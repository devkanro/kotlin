/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.primitive

enum class ILMemberVisibility {
    Assembly,
    CompilerControlled,
    FamilyAndAssembly,
    Family,
    FamilyOrAssembly,
    Private,
    Public,
    ;

    override fun toString(): String =
        when (this) {
            Assembly -> "assembly"
            CompilerControlled -> "compilercontrolled"
            FamilyAndAssembly -> "famandassem"
            Family -> "family"
            FamilyOrAssembly -> "famorassem"
            Private -> "private"
            Public -> "public"
        }
}

