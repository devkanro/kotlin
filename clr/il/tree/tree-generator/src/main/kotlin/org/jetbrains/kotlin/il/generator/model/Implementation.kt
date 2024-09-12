/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator.model

import org.jetbrains.kotlin.generators.tree.AbstractImplementation
import org.jetbrains.kotlin.generators.tree.ImplementationKind
import org.jetbrains.kotlin.generators.tree.imports.ImportCollecting
import org.jetbrains.kotlin.generators.tree.printer.ImportCollectingPrinter

class Implementation(
    element: Element,
    name: String?,
    val delegate: Implementation? = null,
) : AbstractImplementation<Implementation, Element, Field>(element, name) {
    override val allFields: List<Field> = element.allFields.map { it.copy() }

    override var kind: ImplementationKind? = ImplementationKind.FinalClass

    override fun renderTo(appendable: Appendable, importCollector: ImportCollecting) {
        super.renderTo(appendable, importCollector)
    }

    var generationCallback: (ImportCollectingPrinter.() -> Unit)? = null

    override var doPrint = true

    init {
        isPublic = true
    }
}
