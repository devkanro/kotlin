/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

package org.jetbrains.kotlin.il.declarations

import org.jetbrains.kotlin.il.ILElementBase
import org.jetbrains.kotlin.il.primitive.ILCallConv
import org.jetbrains.kotlin.il.primitive.ILClassMember
import org.jetbrains.kotlin.il.primitive.ILId
import org.jetbrains.kotlin.il.primitive.ILParam
import org.jetbrains.kotlin.il.types.ILType

/**
 * Generated from: [org.jetbrains.kotlin.il.generator.ILTree.propertyDecl]
 */
abstract class ILPropertyDecl : ILElementBase(), ILClassMember {
    abstract val hasSpecialName: Boolean
    abstract val hasRuntimeSpecialName: Boolean
    abstract val callConv: ILCallConv
    abstract val type: ILType
    abstract val id: ILId
    abstract val parameters: MutableList<ILParam>
}
