/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

package org.jetbrains.kotlin.il.declarations

import org.jetbrains.kotlin.il.primitive.ILDecl
import org.jetbrains.kotlin.il.primitive.ILDottedName
import org.jetbrains.kotlin.il.primitive.ILManResDecl

/**
 * Generated from: [org.jetbrains.kotlin.il.generator.ILTree.mresourceDecl]
 */
abstract class ILMresourceDecl : ILDecl() {
    abstract val isPrivate: Boolean
    abstract val name: ILDottedName
    abstract val decls: MutableList<ILManResDecl>
}
