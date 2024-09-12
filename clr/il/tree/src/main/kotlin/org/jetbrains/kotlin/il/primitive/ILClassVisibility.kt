/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.primitive

enum class ILClassVisibility {
    Private,
    Public,
    NestedAssembly,
    NestedFamilyAndAssembly,
    NestedFamily,
    NestedFamilyOrAssembly,
    NestedPrivate,
    NestedPublic,
    ;

    override fun toString(): String =
        when (this) {
            Private -> "private"
            Public -> "public"
            NestedAssembly -> "nested assembly"
            NestedFamilyAndAssembly -> "nested famandassem"
            NestedFamily -> "nested family"
            NestedFamilyOrAssembly -> "nested famorassem"
            NestedPrivate -> "nested private"
            NestedPublic -> "nested public"
        }

    val isNested: Boolean
        get() =
            when (this) {
                Private,
                Public,
                -> false
                else -> true
            }
}

