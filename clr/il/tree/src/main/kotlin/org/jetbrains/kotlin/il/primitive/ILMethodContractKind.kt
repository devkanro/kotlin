/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.primitive

enum class ILMethodContractKind {
    Final,
    HideBySignature,
    Static,
    Virtual,
    Strict,
    NewSlot,
    Abstract,
    RuntimeSpecialName,
    SpecialName
    ;

    override fun toString(): String =
        when (this) {
            Final -> "final"
            HideBySignature -> "hidebysig"
            Static -> "static"
            Virtual -> "virtual"
            Strict -> "strict"
            NewSlot -> "newslot"
            Abstract -> "abstract"
            RuntimeSpecialName -> "rtspecialname"
            SpecialName -> "specialname"
        }
}
