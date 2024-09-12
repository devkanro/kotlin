/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator.print

import org.jetbrains.kotlin.generators.tree.printer.ImportCollectingPrinter
import org.jetbrains.kotlin.generators.tree.printer.VariableKind
import org.jetbrains.kotlin.generators.tree.printer.printPropertyDeclaration
import org.jetbrains.kotlin.generators.tree.type
import org.jetbrains.kotlin.utils.withIndent

class OpCodePrinter(
    private val printer: ImportCollectingPrinter,
) {
    fun printOpCode() {
        printer.run {
            println("enum class ILOpCode(")
            withIndent {
                printPropertyDeclaration("label", type<String>(), VariableKind.VAL)
                println(",")
                printPropertyDeclaration("input", type<String>(), VariableKind.VAL)
                println(",")
            }
            print(")")

            OpCodePrinter::class.java.getResourceAsStream("opcode.c").use {
                it.bufferedReader().use {
                    it.forEachLine {
                        when {
                            it.startsWith("OPDEF") -> {
                                val data = it.substring(6, it.length - 1).split(',').map { it.trim() }
                            }
                            it.startsWith("OPALIAS") -> {
                                val data = it.substring(8, it.length - 1)
                            }
                        }
                    }
                }
            }
        }
    }
}
