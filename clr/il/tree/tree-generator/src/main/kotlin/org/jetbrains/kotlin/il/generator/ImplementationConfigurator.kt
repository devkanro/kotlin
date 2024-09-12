/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator

import org.jetbrains.kotlin.generators.tree.ImplementationKind
import org.jetbrains.kotlin.il.generator.config.AbstractILTreeImplementationConfigurator
import org.jetbrains.kotlin.il.generator.model.Implementation

object ImplementationConfigurator : AbstractILTreeImplementationConfigurator() {
    override fun configure(model: Model) =
        with(ILTree) {
            ILTree::class.java.getResourceAsStream("/opcodes.c").use {
                it.reader().useLines {
                    it.forEach {
                        when {
                            it.startsWith("OPDEF") -> instruction(it.substring(6, it.length - 1).split(",").map { it.trim() })
                        }
                    }
                }
            }
        }

    private fun ILTree.instruction(data: List<String>): Implementation =
        impl(opCode, data[0]) {
            kind = ImplementationKind.Object

            default("label", data[1])
            default("input", "ILOpCodeInput.${data[2].replace("+", "")}")
            default("output", "ILOpCodeOutput.${data[3].replace("+", "")}")
            default("param", "ILOpCodeParam.${data[4]}")
            default("type", "ILOpCodeType.${data[5].substring(1)}")

            when (data[6].toInt()) {
                1 -> default("code", "${data[8]}u")
                2 -> default("code", "${data[7]}${data[8].substring(2)}u")
            }

            default("controlFlow", "ILOpCodeControlFlow.${data[9]}")
        }

    override fun configureAllImplementations(model: Model) {
    }
}
