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

object CEE_UNBOX_ANY : ILElementBase(), ILOpCode {
    override val label: String = "unbox.any"
    override val input: ILOpCodeInput = ILOpCodeInput.PopRef
    override val output: ILOpCodeOutput = ILOpCodeOutput.Push1
    override val param: ILOpCodeParam = ILOpCodeParam.InlineType
    override val type: ILOpCodeType = ILOpCodeType.ObjModel
    override val code: UShort = 0xA5u
    override val controlFlow: ILOpCodeControlFlow = ILOpCodeControlFlow.NEXT
}
