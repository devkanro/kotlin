/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.instructions

enum class ILOpCode(
    val label: String,
    val input: ILOpCodeInput,
    val output: ILOpCodeOutput,
    val param: ILOpCodeParam,
    val type: ILOpCodeType,
    val code: UShort,
    val controlFlow: ILOpCodeControlFlow,
) {
    CEE_NOP(
        "nop",
        ILOpCodeInput.Pop0,
        ILOpCodeOutput.Push0,
        ILOpCodeParam.InlineNone,
        ILOpCodeType.Primitive,
        0x00u,
        ILOpCodeControlFlow.NEXT,
    ),
}
