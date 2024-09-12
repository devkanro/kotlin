/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

@file:Suppress("DuplicatedCode")

package org.jetbrains.kotlin.il.declarations.impl

import org.jetbrains.kotlin.il.ILImplementationDetail
import org.jetbrains.kotlin.il.declarations.ILClassDecl
import org.jetbrains.kotlin.il.primitive.ILClassAttr
import org.jetbrains.kotlin.il.primitive.ILClassMember
import org.jetbrains.kotlin.il.primitive.ILGenPar
import org.jetbrains.kotlin.il.primitive.ILId
import org.jetbrains.kotlin.il.types.ILTypeSpec

class ILClassDeclImpl @ILImplementationDetail constructor(
    override val attribs: MutableList<ILClassAttr>,
    override var name: ILId,
    override val genPars: MutableList<ILGenPar>,
    override var extends: ILTypeSpec,
    override val implements: MutableList<ILTypeSpec>,
    override val members: MutableList<ILClassMember>,
) : ILClassDecl()
