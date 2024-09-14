/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr.lower

import org.jetbrains.kotlin.backend.common.FileLoweringPass
import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.backend.jvm.JvmBackendContext
import org.jetbrains.kotlin.ir.backend.clr.ClrBackendContext
import org.jetbrains.kotlin.ir.declarations.IrFile

internal class ClrPropertiesLowering(
    private val backendContext: ClrBackendContext,
) : IrElementTransformerVoidWithContext(), FileLoweringPass {

    override fun lower(irFile: IrFile) {
        irFile.accept(this, null)
    }
}