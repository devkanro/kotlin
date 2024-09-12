/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// This file was generated automatically. See clr/il/tree/tree-generator/ReadMe.md.
// DO NOT MODIFY IT MANUALLY.

@file:Suppress("DuplicatedCode")

package org.jetbrains.kotlin.il.types.impl

import org.jetbrains.kotlin.il.ILImplementationDetail
import org.jetbrains.kotlin.il.primitive.ILDottedName
import org.jetbrains.kotlin.il.primitive.ILResolutionScope
import org.jetbrains.kotlin.il.types.ILTypeReference

class ILTypeReferenceImpl @ILImplementationDetail constructor(
    override var resolutionScope: ILResolutionScope?,
    override var name: ILDottedName,
    override var nested: ILDottedName?,
) : ILTypeReference()
