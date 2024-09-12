/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.primitive

enum class ILPlatformInvokeKind {
    Anis,
    AutoChar,
    Unicode,
    Cdecl,
    FastCall,
    StdCall,
    ThisCall,
    PlatformApi,
    WinApi,
    LastErr,
    Nomangle,
    ;

    override fun toString(): String =
        when (this) {
            Anis -> "anis"
            AutoChar -> "autochar"
            Unicode -> "unicode"
            Cdecl -> "cdecl"
            FastCall -> "fastcall"
            StdCall -> "stdcall"
            ThisCall -> "thiscall"
            PlatformApi -> "platformapi"
            WinApi -> "winapi"
            LastErr -> "lasterr"
            Nomangle -> "nomangle"
        }
}
