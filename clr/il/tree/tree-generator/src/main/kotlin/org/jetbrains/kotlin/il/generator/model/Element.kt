/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator.model

import org.jetbrains.kotlin.generators.tree.AbstractElement
import org.jetbrains.kotlin.il.generator.BASE_PACKAGE

class Element(
    name: String,
    override val propertyName: String,
    val category: Category,
) : AbstractElement<Element, Field, Implementation>(name) {
    enum class Category(
        private val packageDir: String,
        val defaultVisitorParam: String,
    ) {
        Expression("expressions", "expression"),
        Declaration("declarations", "declaration"),
        Primitive("primitive", "primitive"),
        Instruction("instructions", "instruction"),
        Types("types", "primitive"),
        Other("", "element"),
        ;

        val packageName: String get() = BASE_PACKAGE + if (packageDir.isNotEmpty()) ".$packageDir" else ""
    }

    override val namePrefix: String
        get() = "IL"

    override var visitorParameterName = category.defaultVisitorParam

    override val packageName: String = category.packageName
}
