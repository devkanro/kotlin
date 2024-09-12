/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

package org.jetbrains.kotlin.il.declarations

import org.jetbrains.kotlin.il.ILElementBase
import org.jetbrains.kotlin.il.primitive.ILCallConv
import org.jetbrains.kotlin.il.primitive.ILId
import org.jetbrains.kotlin.il.primitive.ILMethodBodyItem
import org.jetbrains.kotlin.il.primitive.ILParam
import org.jetbrains.kotlin.il.types.ILType
import org.jetbrains.kotlin.il.types.ILTypeSpec

/**
 * Generated from: [org.jetbrains.kotlin.il.generator.ILTree.methodOverrideExDecl]
 */
abstract class ILMethodOverrideExDecl : ILElementBase(), ILMethodBodyItem {
    abstract val callConv: ILCallConv
    abstract val returnType: ILType
    abstract val type: ILTypeSpec
    abstract val method: ILId
    abstract val genArity: Int
    abstract val parameters: MutableList<ILParam>
}
