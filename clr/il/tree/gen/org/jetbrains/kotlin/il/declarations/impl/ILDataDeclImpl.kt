/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

@file:Suppress("DuplicatedCode")

package org.jetbrains.kotlin.il.declarations.impl

import org.jetbrains.kotlin.il.ILImplementationDetail
import org.jetbrains.kotlin.il.declarations.ILDataDecl
import org.jetbrains.kotlin.il.declarations.ILDataItem
import org.jetbrains.kotlin.il.primitive.ILId

class ILDataDeclImpl @ILImplementationDetail constructor(
    override var label: ILId,
    override val items: MutableList<ILDataItem>,
) : ILDataDecl()
