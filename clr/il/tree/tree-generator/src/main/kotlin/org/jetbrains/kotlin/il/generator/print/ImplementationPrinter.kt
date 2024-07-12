/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator.print

import org.jetbrains.kotlin.generators.tree.AbstractFieldPrinter
import org.jetbrains.kotlin.generators.tree.AbstractImplementationPrinter
import org.jetbrains.kotlin.generators.tree.ClassRef
import org.jetbrains.kotlin.generators.tree.printer.ImportCollectingPrinter
import org.jetbrains.kotlin.il.generator.elementBaseType
import org.jetbrains.kotlin.il.generator.implementationDetailAnnotationType
import org.jetbrains.kotlin.il.generator.model.Element
import org.jetbrains.kotlin.il.generator.model.Field
import org.jetbrains.kotlin.il.generator.model.Implementation
import org.jetbrains.kotlin.il.generator.model.ListField

private class ImplementationFieldPrinter(
    printer: ImportCollectingPrinter,
) : AbstractFieldPrinter<Field>(printer) {
    override fun forceMutable(field: Field): Boolean = field.isMutable && (field !is ListField)

    override fun actualTypeOfField(field: Field) = field.typeRef

    override val wrapOptInAnnotations
        get() = true
}

internal class ImplementationPrinter(
    printer: ImportCollectingPrinter,
) : AbstractImplementationPrinter<Implementation, Element, Field>(printer) {
    override val implementationOptInAnnotation: ClassRef<*> get() = implementationDetailAnnotationType

    override fun getPureAbstractElementType(implementation: Implementation): ClassRef<*> = elementBaseType

    override fun makeFieldPrinter(printer: ImportCollectingPrinter): AbstractFieldPrinter<Field> = ImplementationFieldPrinter(printer)
}
