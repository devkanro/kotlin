/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator

import org.jetbrains.kotlin.generators.tree.printer.TreeGenerator
import org.jetbrains.kotlin.il.generator.model.Element
import org.jetbrains.kotlin.il.generator.print.ElementPrinter
import org.jetbrains.kotlin.il.generator.print.ImplementationPrinter
import java.io.File

const val BASE_PACKAGE = "org.jetbrains.kotlin.il"

internal const val TREE_GENERATOR_README = "clr/il/tree/tree-generator/ReadMe.md"

typealias Model = org.jetbrains.kotlin.generators.tree.Model<Element>

fun main(args: Array<String>) {
    val generationPath =
        args.firstOrNull()?.let { File(it) }
            ?: File("clr/il/tree/gen").canonicalFile

    val model = ILTree.build()
    TreeGenerator(generationPath, TREE_GENERATOR_README).run {
        generateTree(
            model,
            elementBaseType,
            ::ElementPrinter,
            listOf(),
            ImplementationConfigurator,
            createImplementationPrinter = ::ImplementationPrinter,
            enableBaseTransformerTypeDetection = false,
            addFiles = {
            },
        )
    }
}
