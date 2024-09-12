/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

package org.jetbrains.kotlin.il.declarations

import org.jetbrains.kotlin.il.ILElementBase
import org.jetbrains.kotlin.il.primitive.*
import org.jetbrains.kotlin.il.types.ILType
import org.jetbrains.kotlin.il.types.ILTypeSpec

/**
 * Generated from: [org.jetbrains.kotlin.il.generator.ILTree.eventMethodDecl]
 */
abstract class ILEventMethodDecl : ILElementBase(), ILEventMember {
    abstract val kind: ILEventMethodKind
    abstract val callConv: ILCallConv
    abstract val returnType: ILType
    abstract val declType: ILTypeSpec?
    abstract val methodName: ILDottedName
    abstract val parameters: MutableList<ILParam>
}
