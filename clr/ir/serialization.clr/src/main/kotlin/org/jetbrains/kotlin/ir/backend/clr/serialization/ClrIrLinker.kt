/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr.serialization

import org.jetbrains.kotlin.backend.common.linkage.partial.PartialLinkageSupportForLinker
import org.jetbrains.kotlin.backend.common.overrides.IrLinkerFakeOverrideProvider
import org.jetbrains.kotlin.backend.common.serialization.*
import org.jetbrains.kotlin.backend.common.serialization.encodings.BinarySymbolData
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.ir.builders.TranslationPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.declarations.impl.IrModuleFragmentImpl
import org.jetbrains.kotlin.ir.symbols.IrSymbol
import org.jetbrains.kotlin.ir.types.IrTypeSystemContext
import org.jetbrains.kotlin.ir.util.DeclarationStubGenerator
import org.jetbrains.kotlin.ir.util.IdSignature
import org.jetbrains.kotlin.ir.util.SymbolTable
import org.jetbrains.kotlin.library.IrLibrary
import org.jetbrains.kotlin.library.KotlinAbiVersion
import org.jetbrains.kotlin.library.KotlinLibrary
import org.jetbrains.kotlin.library.metadata.KlibModuleOrigin

class ClrIrLinker(
    currentModule: ModuleDescriptor?,
    messageCollector: MessageCollector,
    typeSystem: IrTypeSystemContext,
    symbolTable: SymbolTable,
    override val translationPluginContext: TranslationPluginContext?,
    private val manglerDesc: ClrDescriptorMangler,
    private val stubGenerator: DeclarationStubGenerator,
) : KotlinIrLinker(currentModule, messageCollector, typeSystem.irBuiltIns, symbolTable, emptyList()) {
    override val fakeOverrideBuilder: IrLinkerFakeOverrideProvider =
        IrLinkerFakeOverrideProvider(
            linker = this,
            symbolTable = symbolTable,
            mangler = ClrIrMangler,
            typeSystem = typeSystem,
            friendModules = emptyMap(), // TODO: provide friend modules
            partialLinkageSupport = PartialLinkageSupportForLinker.DISABLED,
        )

    override fun createModuleDeserializer(
        moduleDescriptor: ModuleDescriptor,
        klib: KotlinLibrary?,
        strategyResolver: (String) -> DeserializationStrategy,
    ): IrModuleDeserializer {
        if (klib != null) {
            assert(moduleDescriptor.getCapability(KlibModuleOrigin.CAPABILITY) != null)
            return ClrModuleDeserializer(moduleDescriptor, klib, klib.versions.abiVersion ?: KotlinAbiVersion.CURRENT, strategyResolver)
        }

        return MetadataClrModuleDeserializer(moduleDescriptor, emptyList())
    }

    override fun isBuiltInModule(moduleDescriptor: ModuleDescriptor): Boolean = false

    private inner class ClrModuleDeserializer(
        moduleDescriptor: ModuleDescriptor,
        klib: IrLibrary,
        libraryAbiVersion: KotlinAbiVersion,
        strategyResolver: (String) -> DeserializationStrategy,
    ) : BasicIrModuleDeserializer(this, moduleDescriptor, klib, strategyResolver, libraryAbiVersion)

    private inner class MetadataClrModuleDeserializer(
        moduleDescriptor: ModuleDescriptor,
        dependencies: List<IrModuleDeserializer>,
    ) : IrModuleDeserializer(moduleDescriptor, KotlinAbiVersion.CURRENT) {
        // TODO: implement proper check whether `idSig` belongs to this module
        override fun contains(idSig: IdSignature): Boolean = true

        private val descriptorFinder =
            DescriptorByIdSignatureFinderImpl(
                moduleDescriptor,
                manglerDesc,
                DescriptorByIdSignatureFinderImpl.LookupMode.MODULE_ONLY,
            )

        private fun resolveDescriptor(idSig: IdSignature): DeclarationDescriptor? = descriptorFinder.findDescriptorBySignature(idSig)

        override fun tryDeserializeIrSymbol(
            idSig: IdSignature,
            symbolKind: BinarySymbolData.SymbolKind,
        ): IrSymbol? {
            val descriptor = resolveDescriptor(idSig) ?: return null

            val declaration =
                stubGenerator.run {
                    when (symbolKind) {
                        BinarySymbolData.SymbolKind.FIELD_SYMBOL -> generateFieldStub(descriptor as PropertyDescriptor)
                        BinarySymbolData.SymbolKind.CLASS_SYMBOL -> generateClassStub(descriptor as ClassDescriptor)
                        BinarySymbolData.SymbolKind.PROPERTY_SYMBOL -> generatePropertyStub(descriptor as PropertyDescriptor)
                        BinarySymbolData.SymbolKind.FUNCTION_SYMBOL -> generateFunctionStub(descriptor as FunctionDescriptor)
                        BinarySymbolData.SymbolKind.CONSTRUCTOR_SYMBOL -> generateConstructorStub(descriptor as ClassConstructorDescriptor)
                        BinarySymbolData.SymbolKind.ENUM_ENTRY_SYMBOL -> generateEnumEntryStub(descriptor as ClassDescriptor)
                        BinarySymbolData.SymbolKind.TYPEALIAS_SYMBOL -> generateTypeAliasStub(descriptor as TypeAliasDescriptor)
                        else -> error("Unexpected type $symbolKind for sig $idSig")
                    }
                }

            return declaration.symbol
        }

        override fun deserializedSymbolNotFound(idSig: IdSignature): Nothing = error("No descriptor found for $idSig")

        override val moduleFragment: IrModuleFragment = IrModuleFragmentImpl(moduleDescriptor, builtIns)
        override val moduleDependencies: Collection<IrModuleDeserializer> = dependencies

        override val kind get() = IrModuleDeserializerKind.SYNTHETIC
    }
}
