/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr.serialization

import org.jetbrains.kotlin.backend.common.serialization.mangle.KotlinExportChecker
import org.jetbrains.kotlin.backend.common.serialization.mangle.KotlinMangleComputer
import org.jetbrains.kotlin.backend.common.serialization.mangle.MangleMode
import org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorBasedKotlinManglerImpl
import org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor
import org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorMangleComputer
import org.jetbrains.kotlin.backend.common.serialization.mangle.ir.IrBasedKotlinManglerImpl
import org.jetbrains.kotlin.backend.common.serialization.mangle.ir.IrExportCheckerVisitor
import org.jetbrains.kotlin.backend.common.serialization.mangle.ir.IrMangleComputer
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.idea.MainFunctionDetector
import org.jetbrains.kotlin.ir.declarations.IrDeclaration
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.types.KotlinType

object ClrIrMangler : IrBasedKotlinManglerImpl() {
    private class ClrIrExportChecker(
        compatibleMode: Boolean,
    ) : IrExportCheckerVisitor(compatibleMode) {
        override fun IrDeclaration.isPlatformSpecificExported() = false
    }

    private class ClrIrManglerComputer(
        builder: StringBuilder,
        mode: MangleMode,
        compatibleMode: Boolean,
    ) : IrMangleComputer(builder, mode, compatibleMode) {
        override fun copy(newMode: MangleMode): IrMangleComputer = ClrIrManglerComputer(builder, newMode, compatibleMode)

        override fun addReturnTypeSpecialCase(function: IrFunction): Boolean = true

        override fun mangleTypePlatformSpecific(
            type: IrType,
            tBuilder: StringBuilder,
        ) {
        }
    }

    override fun getExportChecker(compatibleMode: Boolean): KotlinExportChecker<IrDeclaration> = ClrIrExportChecker(compatibleMode)

    override fun getMangleComputer(
        mode: MangleMode,
        compatibleMode: Boolean,
    ): KotlinMangleComputer<IrDeclaration> = ClrIrManglerComputer(StringBuilder(256), mode, compatibleMode)
}

class ClrDescriptorMangler(
    private val mainDetector: MainFunctionDetector?,
) : DescriptorBasedKotlinManglerImpl() {
    private object ExportChecker : DescriptorExportCheckerVisitor() {
        override fun DeclarationDescriptor.isPlatformSpecificExported() = true
    }

    private class ClrDescriptorManglerComputer(
        builder: StringBuilder,
        mode: MangleMode,
    ) : DescriptorMangleComputer(builder, mode) {
        override fun addReturnTypeSpecialCase(function: FunctionDescriptor): Boolean = true

        override fun copy(newMode: MangleMode): DescriptorMangleComputer = ClrDescriptorManglerComputer(builder, newMode)

        override fun visitModuleDeclaration(descriptor: ModuleDescriptor) {
        }

        override fun mangleTypePlatformSpecific(
            type: KotlinType,
            tBuilder: StringBuilder,
        ) {
        }
    }

    override fun getExportChecker(compatibleMode: Boolean): KotlinExportChecker<DeclarationDescriptor> = ExportChecker

    override fun getMangleComputer(
        mode: MangleMode,
        compatibleMode: Boolean,
    ): KotlinMangleComputer<DeclarationDescriptor> = ClrDescriptorManglerComputer(StringBuilder(256), mode)
}
