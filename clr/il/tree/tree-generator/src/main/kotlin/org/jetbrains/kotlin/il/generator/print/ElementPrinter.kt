/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator.print

import org.jetbrains.kotlin.generators.tree.AbstractElementPrinter
import org.jetbrains.kotlin.generators.tree.AbstractFieldPrinter
import org.jetbrains.kotlin.generators.tree.printer.ImportCollectingPrinter
import org.jetbrains.kotlin.il.generator.model.Element
import org.jetbrains.kotlin.il.generator.model.Field

internal class ElementPrinter(
    printer: ImportCollectingPrinter,
) : AbstractElementPrinter<Element, Field>(printer) {
    override fun makeFieldPrinter(printer: ImportCollectingPrinter) = object : AbstractFieldPrinter<Field>(printer) {}

    override fun ImportCollectingPrinter.printAdditionalMethods(element: Element) {
    }
}
