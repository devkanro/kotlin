/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.types

interface ILNativeType {
    override fun toString(): String
}

data class ILIntBaseNativeType internal constructor(
    val unsigned: Boolean,
    val name: String,
) {
    override fun toString(): String {
        if (unsigned) {
            return "unsigned $name"
        } else {
            return name
        }
    }
}

object ILNativeTypes {
    object Array : ILNativeType {
        override fun toString(): String = "[]"
    }

    val Int = ILIntBaseNativeType(false, "int")

    val UInt = ILIntBaseNativeType(true, "int")

    val Int8 = ILIntBaseNativeType(false, "int8")

    val UInt8 = ILIntBaseNativeType(true, "int8")

    val Int16 = ILIntBaseNativeType(false, "int16")

    val UInt16 = ILIntBaseNativeType(true, "int16")

    val Int32 = ILIntBaseNativeType(false, "int32")

    val UInt32 = ILIntBaseNativeType(true, "int32")

    val Int64 = ILIntBaseNativeType(false, "int64")

    val UInt64 = ILIntBaseNativeType(true, "int64")

    object LPStr : ILNativeType {
        override fun toString(): String = "lpstr"
    }

    object LPWStr : ILNativeType {
        override fun toString(): String = "lpwstr"
    }

    object FunPointer : ILNativeType {
        override fun toString(): String = "method"
    }

    data class NativeTypeArray(
        val type: ILNativeType,
        val length: Int? = null,
        val dynamicLen: Int? = null,
    ) : ILNativeType {
        override fun toString(): String =
            when {
                length == null && dynamicLen == null -> "$type[]"
                length != null && dynamicLen != null -> "$type[$length+$dynamicLen]"
                length != null -> "$type[$length]"
                else -> "$type[+$dynamicLen]"
            }
    }

    object AsAny : ILNativeType {
        override fun toString(): String = "as any"
    }

    object ByValStr : ILNativeType {
        override fun toString(): String = "byvalstr"
    }

    data class Custom(
        val marshallingClass: String,
        val arbitrary: String,
    ) : ILNativeType {
        override fun toString(): String = "custom(\"$marshallingClass\", \"$arbitrary\")"
    }

    data class FixedArray(
        val length: Int,
    ) : ILNativeType {
        override fun toString(): String = "fixed array[$length]"
    }

    data class FixedSysString(
        val length: Int,
    ) : ILNativeType {
        override fun toString(): String = "fixed sysstring[$length]"
    }

    object LPStruct : ILNativeType {
        override fun toString(): String = "lpstruct"
    }

    object LPTStr : ILNativeType {
        override fun toString(): String = "lptstr"
    }

    object Struct : ILNativeType {
        override fun toString(): String = "struct"
    }
}
