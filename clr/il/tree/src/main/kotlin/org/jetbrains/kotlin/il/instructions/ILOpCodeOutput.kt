/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.instructions

enum class ILOpCodeOutput {
    /**
     * no output value
     */
    Push0,

    /**
     * one output value, type defined by data flow.
     */
    Push1,

    /**
     * two output values, type defined by data flow
     */
    Push1Push1,

    /**
     * push one native integer or pointer
     */
    PushI,

    /**
     * push one 8-byte integer
     */
    PushI8,

    /**
     * push one 4-byte floating point number
     */
    PushR4,

    /**
     * push one 8-byte floating point number
     */
    PushR8,

    /**
     * push one object reference
     */
    PushRef,

    /**
     * variable number of items pushed, see Partition III for details
     */
    VarPush,

    ;

    override fun toString(): String =
        when (this) {
            VarPush -> name
            else -> name.replace(nameConversionRegex, "$1+P")
        }

    companion object {
        private val nameConversionRegex = """(\w)P""".toRegex()

        operator fun invoke(value: String): ILOpCodeOutput {
            ILOpCodeOutput.entries.forEach {
                if (it.toString() == value) {
                    return it
                }
            }

            throw IllegalArgumentException("Not supported ILOpCodeOutput '$value'.")
        }
    }
}
