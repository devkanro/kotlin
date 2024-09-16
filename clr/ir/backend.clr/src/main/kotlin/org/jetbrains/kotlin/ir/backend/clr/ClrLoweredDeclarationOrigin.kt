/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr

import org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl

object ClrLoweredDeclarationOrigin {
    val FIELD_FOR_OUTER_THIS by IrDeclarationOriginImpl
}
