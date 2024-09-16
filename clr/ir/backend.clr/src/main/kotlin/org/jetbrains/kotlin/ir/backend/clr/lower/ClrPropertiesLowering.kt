/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr.lower

import org.jetbrains.kotlin.backend.common.FileLoweringPass
import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.backend.clr.ClrBackendContext
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrDeclaration
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.util.transformDeclarationsFlat
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid

internal class ClrPropertiesLowering(
    private val backendContext: ClrBackendContext,
) : IrElementTransformerVoidWithContext(),
    FileLoweringPass {
    override fun lower(irFile: IrFile) {
        irFile.accept(this, null)
    }

    override fun visitClassNew(declaration: IrClass): IrStatement {
        declaration.transformChildrenVoid(this)
        declaration.transformDeclarationsFlat { if (it is IrProperty) lowerProperty(it, declaration.kind) else null }
        return declaration
    }

    private fun lowerProperty(
        declaration: IrProperty,
        kind: ClassKind,
    ): List<IrDeclaration> = listOf()
}
