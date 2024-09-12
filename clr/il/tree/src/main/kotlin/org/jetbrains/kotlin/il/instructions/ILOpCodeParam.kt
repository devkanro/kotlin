/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.instructions

enum class ILOpCodeParam {
    /**
     * Branch target, represented as a 4-byte signed integer from the beginning of the instruction following the current instruction.
     */
    InlineBrTarget,

    /**
     * Metadata token (4 bytes) representing a FieldRef (i.e., a MemberRef to a field) or FieldDef
     */
    InlineField,

    /**
     * 4-byte integer
     */
    InlineI,

    /**
     * 8-byte integer
     */
    InlineI8,

    /**
     * Metadata token (4 bytes) representing a MethodRef (i.e., a MemberRef to a method) or MethodDef
     */
    InlineMethod,

    /**
     * No in-line argument
     */
    InlineNone,

    /**
     * 8-byte floating point number
     */
    InlineR,

    /**
     * Metadata token (4 bytes) representing a standalone signature
     */
    InlineSig,

    /**
     * Metadata token (4 bytes) representing a UserString
     */
    InlineString,

    /**
     * Special for the switch instructions, see Partition III for details
     */
    InlineSwitch,

    /**
     * Arbitrary metadata token (4 bytes) , used for ldtoken instruction, see Partition III for details
     */
    InlineTok,

    /**
     * Metadata token (4 bytes) representing a TypeDef, TypeRef, or TypeSpec
     */
    InlineType,

    /**
     * 2-byte integer representing an argument or local variable
     */
    InlineVar,

    /**
     * Short branch target, represented as 1 signed byte from the beginning of the instruction following the current instruction.
     */
    ShortInlineBrTarget,

    /**
     * 1-byte integer, signed or unsigned depending on instruction
     */
    ShortInlineI,

    /**
     * 4-byte floating point number
     */
    ShortInlineR,

    /**
     * 1-byte integer representing an argument or local variable
     */
    ShortInlineVar,
}
