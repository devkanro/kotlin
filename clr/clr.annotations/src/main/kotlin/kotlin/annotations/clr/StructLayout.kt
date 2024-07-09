/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.annotations.clr

/**
 * Lets you control the physical layout of the data fields of a class or structure in memory.
 */
annotation class StructLayout(
    /**
     * Controls the alignment of data fields of a class or structure in memory.
     */
    val pack: LayoutKind,
    /**
     * Indicates the absolute size of the class or structure.
     */
    val size: Int,
    /**
     * Indicates whether string data fields within the class should be marshaled as LPWSTR or LPSTR by default.     *
     */
    val charSet: CharSet = CharSet.Ansi
)

