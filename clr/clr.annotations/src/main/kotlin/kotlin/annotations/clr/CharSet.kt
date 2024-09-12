/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.annotations.clr

/**
 * Dictates which character set marshaled strings should use.
 */
enum class CharSet(val value: Int) {
    /**
     * Marshal strings as multiple-byte character strings:
     * the system default Windows (ANSI) code page on Windows,
     * and UTF-8 on Unix.
     */
    Ansi(2),

    /**
     * Automatically marshal strings appropriately for the target operating system.
     * See Charsets and marshaling for details.
     * Although the common language runtime default is Auto,
     * languages may override this default.
     * For example, by default C# and Visual Basic mark all methods and types as Ansi.
     */
    Auto(4),

    /**
     * This value is obsolete and has the same behavior as [Ansi].
     */
    @Deprecated("This value is obsolete and has the same behavior as Ansi.", ReplaceWith("Ansi"))
    None(1),

    /**
     * Marshal strings as Unicode 2-byte character strings.
     */
    Unicode(3)
}