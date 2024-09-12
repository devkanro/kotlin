/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

@file:Suppress("DuplicatedCode")

package org.jetbrains.kotlin.il.declarations.impl

import org.jetbrains.kotlin.il.ILImplementationDetail
import org.jetbrains.kotlin.il.declarations.ILOverrideDecl
import org.jetbrains.kotlin.il.primitive.ILCallConv
import org.jetbrains.kotlin.il.primitive.ILId
import org.jetbrains.kotlin.il.primitive.ILParam
import org.jetbrains.kotlin.il.types.ILType
import org.jetbrains.kotlin.il.types.ILTypeSpec

class ILOverrideDeclImpl @ILImplementationDetail constructor(
    override var type: ILTypeSpec,
    override var method: ILId,
    override var implType: ILTypeSpec,
    override var implMethod: ILId,
    override var callConv: ILCallConv,
    override var returnType: ILType,
    override val parameters: MutableList<ILParam>,
) : ILOverrideDecl()
