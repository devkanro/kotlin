/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

package org.jetbrains.kotlin.il.declarations

import org.jetbrains.kotlin.il.primitive.*
import org.jetbrains.kotlin.il.types.ILNativeType
import org.jetbrains.kotlin.il.types.ILType

/**
 * Generated from: [org.jetbrains.kotlin.il.generator.ILTree.methodDecl]
 */
abstract class ILMethodDecl : ILDecl(), ILClassMember {
    abstract val attribs: MutableList<ILMethodAttr>
    abstract val callConv: ILCallConv
    abstract val returnType: ILType
    abstract val marshalAs: ILNativeType?
    abstract val id: ILId
    abstract val genPars: MutableList<ILGenPar>
    abstract val parameters: MutableList<ILParam>
    abstract val implAttribs: MutableList<ILMethodImplAttrKind>
    abstract val items: MutableList<ILMethodBodyItem>
}
