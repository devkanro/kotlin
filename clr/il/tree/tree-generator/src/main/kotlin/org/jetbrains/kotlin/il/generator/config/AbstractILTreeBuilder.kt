/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator.config

import org.jetbrains.kotlin.generators.tree.StandardTypes
import org.jetbrains.kotlin.generators.tree.TypeRef
import org.jetbrains.kotlin.generators.tree.TypeRefWithNullability
import org.jetbrains.kotlin.generators.tree.config.AbstractElementConfigurator
import org.jetbrains.kotlin.generators.tree.type
import org.jetbrains.kotlin.il.generator.model.Element
import org.jetbrains.kotlin.il.generator.model.Field
import org.jetbrains.kotlin.il.generator.model.ListField
import org.jetbrains.kotlin.il.generator.model.SimpleField

abstract class AbstractILTreeBuilder : AbstractElementConfigurator<Element, Field, Element.Category>() {
    override fun createElement(
        name: String,
        propertyName: String,
        category: Element.Category,
    ): Element = Element(name, propertyName, category)

    protected fun field(
        name: String,
        type: TypeRefWithNullability,
        nullable: Boolean = false,
        mutable: Boolean = true,
        isChild: Boolean = true,
        initializer: SimpleField.() -> Unit = {},
    ): SimpleField =
        SimpleField(name, type.copy(nullable), mutable, isChild).apply {
            initializer()
        }

    protected inline fun <reified T : Any> field(
        name: String,
        nullable: Boolean = false,
        mutable: Boolean = true,
        isChild: Boolean = true,
        noinline initializer: SimpleField.() -> Unit = {},
    ): SimpleField = field(name, type<T>(), nullable, mutable, isChild, initializer)

    protected fun listField(
        name: String,
        baseType: TypeRef,
        nullable: Boolean = false,
        mutability: ListField.Mutability = ListField.Mutability.MutableList,
        isChild: Boolean = true,
        initializer: ListField.() -> Unit = {},
    ): ListField {
        val listType =
            when (mutability) {
                ListField.Mutability.MutableList -> StandardTypes.mutableList
                ListField.Mutability.Array -> StandardTypes.array
                else -> StandardTypes.list
            }
        return ListField(
            name = name,
            baseType = baseType,
            listType = listType,
            isNullable = nullable,
            mutable = mutability == ListField.Mutability.Var,
            isChild = isChild,
        ).apply(initializer).apply {
            initializer()
        }
    }

    protected inline fun <reified T : Any> listField(
        name: String,
        nullable: Boolean = false,
        mutability: ListField.Mutability = ListField.Mutability.MutableList,
        isChild: Boolean = true,
        noinline initializer: ListField.() -> Unit = {},
    ): ListField = listField(name, type<T>(), nullable, mutability, isChild, initializer)

    protected fun Element.generateBooleanFields(vararg names: String) {
        names.forEach {
            +field(
                if (it.startsWith("is") || it.startsWith("has")) it else "is${it.replaceFirstChar(Char::uppercaseChar)}",
                StandardTypes.boolean,
            )
        }
    }
}
