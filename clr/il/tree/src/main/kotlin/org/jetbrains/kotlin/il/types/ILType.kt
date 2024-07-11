/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.types

interface ILType {
    override fun toString(): String
}

sealed class ILBuiltInType(
    private val name: String,
) : ILType {
    override fun toString(): String = name
}

object ILBuiltInTypes {
    /**
     * Boolean
     */
    object Bool : ILBuiltInType("bool")

    /**
     * 16-bit Unicode code point
     */
    object Char : ILBuiltInType("char")

    object Float32 : ILBuiltInType("float32")

    object Float64 : ILBuiltInType("float64")

    object Int8 : ILBuiltInType("int8")

    object Int16 : ILBuiltInType("int16")

    object Int32 : ILBuiltInType("int32")

    object Int64 : ILBuiltInType("int64")

    object NativeInt : ILBuiltInType("native int")

    object NativeUnsignedInt : ILBuiltInType("native unsigned int")

    object Object : ILBuiltInType("object")

    object String : ILBuiltInType("string")

    object UnsignedInt8 : ILBuiltInType("unsigned int8")

    object UnsignedInt16 : ILBuiltInType("unsigned int16")

    object UnsignedInt32 : ILBuiltInType("unsigned int32")

    object UnsignedInt64 : ILBuiltInType("unsigned int64")

    object Void : ILBuiltInType("void")

    object Typedref: ILBuiltInType("typedref")
}
