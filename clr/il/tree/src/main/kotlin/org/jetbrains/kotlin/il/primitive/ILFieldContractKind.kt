/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.primitive

enum class ILFieldContractKind {
    InitOnly,
    Literal,
    Static,
    NotSerialized,
    ;

    override fun toString(): String =
        when (this) {
            InitOnly -> "initonly"
            Literal -> "literal"
            Static -> "static"
            NotSerialized -> "notserialized"
        }
}
