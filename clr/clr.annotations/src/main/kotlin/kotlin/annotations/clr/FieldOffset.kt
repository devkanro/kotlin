/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.annotations.clr

/**
 * Indicates the physical position of fields within the unmanaged representation of a class or structure.
 */
annotation class FieldOffset(val offset: Int)
