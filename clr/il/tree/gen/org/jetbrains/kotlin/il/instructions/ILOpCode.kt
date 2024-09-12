/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

package org.jetbrains.kotlin.il.instructions

import org.jetbrains.kotlin.il.ILElement

/**
 * Generated from: [org.jetbrains.kotlin.il.generator.ILTree.opCode]
 */
interface ILOpCode : ILElement {
    val label: String
    val input: ILOpCodeInput
    val output: ILOpCodeOutput
    val param: ILOpCodeParam
    val type: ILOpCodeType
    val code: UShort
    val controlFlow: ILOpCodeControlFlow
}
