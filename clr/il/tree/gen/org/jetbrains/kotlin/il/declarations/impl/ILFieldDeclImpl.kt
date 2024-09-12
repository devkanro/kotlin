/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

@file:Suppress("DuplicatedCode")

package org.jetbrains.kotlin.il.declarations.impl

import org.jetbrains.kotlin.il.ILImplementationDetail
import org.jetbrains.kotlin.il.declarations.ILFieldDecl
import org.jetbrains.kotlin.il.primitive.ILFieldAttr
import org.jetbrains.kotlin.il.primitive.ILFieldInitDecl
import org.jetbrains.kotlin.il.primitive.ILId
import org.jetbrains.kotlin.il.types.ILType

class ILFieldDeclImpl @ILImplementationDetail constructor(
    override var offset: Int?,
    override val attribs: MutableList<ILFieldAttr>,
    override var type: ILType,
    override var id: ILId,
    override var init: ILFieldInitDecl?,
    override var data: ILId?,
) : ILFieldDecl()
