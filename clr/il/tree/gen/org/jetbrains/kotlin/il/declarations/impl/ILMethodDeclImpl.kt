/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

@file:Suppress("DuplicatedCode")

package org.jetbrains.kotlin.il.declarations.impl

import org.jetbrains.kotlin.il.ILImplementationDetail
import org.jetbrains.kotlin.il.declarations.ILMethodDecl
import org.jetbrains.kotlin.il.primitive.*
import org.jetbrains.kotlin.il.types.ILNativeType
import org.jetbrains.kotlin.il.types.ILType

class ILMethodDeclImpl @ILImplementationDetail constructor(
    override val attribs: MutableList<ILMethodAttr>,
    override var callConv: ILCallConv,
    override var returnType: ILType,
    override var marshalAs: ILNativeType?,
    override var id: ILId,
    override val genPars: MutableList<ILGenPar>,
    override val parameters: MutableList<ILParam>,
    override val implAttribs: MutableList<ILMethodImplAttrKind>,
    override val items: MutableList<ILMethodBodyItem>,
) : ILMethodDecl()
