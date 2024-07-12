/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

package org.jetbrains.kotlin.il.primitive

import org.jetbrains.kotlin.il.ILElement
import org.jetbrains.kotlin.il.ILElementBase
import org.jetbrains.kotlin.il.types.ILNativeType
import org.jetbrains.kotlin.il.types.ILType

/**
 * Generated from: [org.jetbrains.kotlin.il.generator.ILTree.param]
 */
abstract class ILParam : ILElementBase(), ILElement {
    abstract val hasInAtt: Boolean
    abstract val hasOutAtt: Boolean
    abstract val hasOptAtt: Boolean
    abstract val type: ILType
    abstract val marshalAs: ILNativeType
    abstract val name: ILId?
}
