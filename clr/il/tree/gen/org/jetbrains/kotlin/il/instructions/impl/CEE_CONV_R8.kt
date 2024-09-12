/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

@file:Suppress("DuplicatedCode")

package org.jetbrains.kotlin.il.instructions.impl

import org.jetbrains.kotlin.il.ILElementBase
import org.jetbrains.kotlin.il.instructions.*

object CEE_CONV_R8 : ILElementBase(), ILOpCode {
    override val label: String = "conv.r8"
    override val input: ILOpCodeInput = ILOpCodeInput.Pop1
    override val output: ILOpCodeOutput = ILOpCodeOutput.PushR8
    override val param: ILOpCodeParam = ILOpCodeParam.InlineNone
    override val type: ILOpCodeType = ILOpCodeType.Primitive
    override val code: UShort = 0x6Cu
    override val controlFlow: ILOpCodeControlFlow = ILOpCodeControlFlow.NEXT
}
