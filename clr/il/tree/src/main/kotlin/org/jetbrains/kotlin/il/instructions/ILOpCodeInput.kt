/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.instructions

enum class ILOpCodeInput {
    /**
     * no inputs
     */
    Pop0,

    /**
     * one value type specified by data flow
     */
    Pop1,

    /**
     * two input values, types specified by data flow
     */
    Pop1Pop1,

    /**
     * one machine-sized integer
     */
    PopI,

    /**
     * Top of stack is described by data flow, next item is a native pointer
     */
    PopIPop1,

    /**
     * Top two items on stack are integers (size can vary by instruction)
     */
    PopIPopI,

    /**
     * Top three items on stack are machine-sized integers
     */
    PopIPopIPopI,

    /**
     * Top of stack is an 8-byte integer, next is a native pointer
     */
    PopI8Pop8,

    /**
     * Top of stack is a 4-byte floating point number, next is a native pointer
     */
    PopIPopR4,

    /**
     * Top of stack is an 8-byte floating point number, next is a native pointer
     */
    PopIPopR8,

    /**
     * Top of stack is an object reference
     */
    PopRef,

    /**
     * Top of stack is an integer (size can vary by instruction), next is an object reference
     */
    PopRefPopI,

    /**
     * Top of stack has two integers (size can vary by instruction), next is an object reference
     */
    PopRefPopIPopI,

    /**
     * Top of stack is an 8-byte integer, then a native-sized integer, then an object reference
     */
    PopRefPopIPopI8,

    /**
     * Top of stack is an 4-byte floating point number, then a native-sized integer, then an object reference
     */
    PopRefPopIPopR4,

    /**
     * Top of stack is an 8-byte floating point number, then a native-sized integer, then an object reference
     */
    PopRefPopIPopR8,

    /**
     * variable number of items used, see Partition III for details
     */
    VarPop,

    ;

    override fun toString(): String =
        when (this) {
            VarPop -> name
            else -> name.replace(nameConversionRegex, "$1+P")
        }

    companion object {
        private val nameConversionRegex = """(\w)P""".toRegex()

        operator fun invoke(value: String): ILOpCodeInput {
            entries.forEach {
                if (it.toString() == value) {
                    return it
                }
            }

            throw IllegalArgumentException("Not supported ILOpCodeInput '$value'.")
        }
    }
}
