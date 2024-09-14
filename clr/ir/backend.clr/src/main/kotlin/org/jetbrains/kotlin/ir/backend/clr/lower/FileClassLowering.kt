/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr.lower

import com.intellij.openapi.util.io.FileUtil
import org.jetbrains.kotlin.backend.common.FileLoweringPass
import org.jetbrains.kotlin.backend.common.phaser.PhaseDescription
import org.jetbrains.kotlin.backend.jvm.JvmBackendContext
import org.jetbrains.kotlin.codegen.AsmUtil
import org.jetbrains.kotlin.config.JvmAnalysisFlags
import org.jetbrains.kotlin.config.LanguageFeature
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.fileClasses.JvmFileClassInfo
import org.jetbrains.kotlin.fileClasses.JvmFileClassUtil
import org.jetbrains.kotlin.ir.PsiIrFileEntry
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.backend.clr.ClrBackendContext
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.symbols.impl.IrClassSymbolImpl
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.load.java.JavaDescriptorVisibilities
import org.jetbrains.kotlin.load.kotlin.PackagePartClassUtils
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.inline.INLINE_ONLY_ANNOTATION_FQ_NAME
import org.jetbrains.kotlin.resolve.jvm.JvmClassName
import java.io.File

@PhaseDescription(
    name = "FileClass",
    description = "Put file level function and property declaration into a class",
)
internal class FileClassLowering(val context: ClrBackendContext) : FileLoweringPass {

    override fun lower(irFile: IrFile) {
        val classes = ArrayList<IrClass>()
        val fileClassMembers = ArrayList<IrDeclaration>()

        irFile.declarations.forEach {
            when (it) {
                is IrScript -> {
                }
                is IrClass -> classes.add(it)
                else -> fileClassMembers.add(it)
            }
        }

        if (fileClassMembers.isEmpty() && (irFile.metadata as? DescriptorMetadataSource.File)?.descriptors.isNullOrEmpty()) return

        val irFileClass = createFileClass(irFile, fileClassMembers)
        classes.add(irFileClass)

        irFile.declarations.clear()
        irFile.declarations.addAll(classes)
    }

    private fun createFileClass(irFile: IrFile, fileClassMembers: List<IrDeclaration>): IrClass {
        val fileEntry = irFile.fileEntry

        return context.irFactory.createClass(
            startOffset = if (fileEntry.maxOffset == UNDEFINED_OFFSET) UNDEFINED_OFFSET else 0,
            endOffset = fileEntry.maxOffset,
            origin = IrDeclarationOrigin.FILE_CLASS,
            name = Name.identifier(PackagePartClassUtils.getFilePartShortName(irFile.path)),
            visibility = DescriptorVisibilities.PUBLIC,
            symbol = IrClassSymbolImpl(),
            kind = ClassKind.CLASS,
            modality = Modality.FINAL,
        ).apply {
            superTypes = listOf(context.irBuiltIns.anyType)
            parent = irFile
            declarations.addAll(fileClassMembers)
            createImplicitParameterDeclarationWithWrappedDescriptor()
            for (member in fileClassMembers) {
                member.parent = this
                if (member is IrProperty) {
                    member.getter?.let { it.parent = this }
                    member.setter?.let { it.parent = this }
                    member.backingField?.let { it.parent = this }
                }
            }

            annotations = irFile.annotations

            metadata = irFile.metadata

            val partClassType = AsmUtil.asmTypeByFqNameWithoutInnerClasses(fileClassInfo.fileClassFqName)
            val facadeClassType = if (isMultifilePart) AsmUtil.asmTypeByFqNameWithoutInnerClasses(fileClassInfo.facadeClassFqName)
            else null
            context.state.factory.packagePartRegistry.addPart(
                irFile.packageFqName, partClassType.internalName, facadeClassType?.internalName
            )
        }
    }
}
