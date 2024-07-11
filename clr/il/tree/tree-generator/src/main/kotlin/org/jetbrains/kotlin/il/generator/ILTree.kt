/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator

import org.jetbrains.kotlin.descriptors.Visibility
import org.jetbrains.kotlin.generators.tree.type
import org.jetbrains.kotlin.il.generator.config.AbstractILTreeBuilder
import org.jetbrains.kotlin.il.generator.model.Element
import org.jetbrains.kotlin.il.generator.model.ListField
import org.jetbrains.kotlin.il.generator.model.SimpleField

object ILTree : AbstractILTreeBuilder() {
    private fun descriptor(
        typeName: String,
        nullable: Boolean = false,
    ): SimpleField =
        field(
            name = "descriptor",
            type = type(Packages.descriptors, typeName),
            mutable = false,
            nullable = nullable,
        ) {
        }

    override val rootElement: Element by element(Element.Category.Other, name = "Element") {
        kDoc = "The root interface of the IL tree. Each IL node implements this interface."
    }

    val assemblyElement: Element by element(Element.Category.Primitive) {
    }

    val moduleElement: Element by element(Element.Category.Primitive) {
    }

    val classElement: Element by element(Element.Category.Primitive) {
    }

    val namedElement: Element by element(Element.Category.Primitive) {
        +field("id", type<String>())
    }

    val externSourceDeclaration: Element by element(Element.Category.Primitive) {
        parent(assemblyElement)
        parent(moduleElement)
        parent(classElement)

        +field("file", type<String>())
        +field("line", type<Int>())
        +field("column", type<Int>(), nullable = true)
    }

    val fileAlignment: Element by element(Element.Category.Primitive) {
        parent(assemblyElement)

        +field("alignment", type<Int>())
    }

    val imageBase: Element by element(Element.Category.Primitive) {
        parent(assemblyElement)

        +field("value", type<Int>())
    }

    val subsystem: Element by element(Element.Category.Primitive) {
        parent(assemblyElement)

        +field("value", type<Int>())
    }

    val moduleReference: Element by element(Element.Category.Types) {
        parent(assemblyElement)
        parent(moduleElement)

        +field("filename", type<String>())
    }

    val typeReference: Element by sealedElement(Element.Category.Types) {
        +field("name", type<String>())
        +field("nestedName", type<String>())
    }

    val simpleTypeReference: Element by element(Element.Category.Types) {
        parent(typeReference)
    }

    val moduleTypeReference: Element by element(Element.Category.Types) {
        parent(typeReference)

        +field("moduleName", type<String>())
    }

    val assemblyTypeReference: Element by element(Element.Category.Types) {
        parent(typeReference)

        +field("assemblyName", type<String>())
    }

    val `class`: Element by element(Element.Category.Declaration) {
        parent(moduleElement)
        parent(classElement)

        +field("visibility", type<Visibility>())
        +field("layout", layoutAttributeType)
        +field("isInterface", type<Boolean>())
        +field("isAbstract", type<Boolean>())
        +field("isSealed", type<Boolean>())
        +listField("genericParameters", genericParameter, mutability = ListField.Mutability.MutableList)
        +listField("declarations", classElement, mutability = ListField.Mutability.MutableList)
    }

    val genericParameter: Element by element(Element.Category.Declaration) {
        +field("isClass", type<Boolean>())
        +field("isValueType", type<Boolean>())
        +field("hasDefaultConstructor", type<Boolean>())
        +field("isCovariant", type<Boolean>())
        +field("isContravariant", type<Boolean>())

        +listField("constraints", typeType, mutability = ListField.Mutability.MutableList)
    }

    val data: Element by element(Element.Category.Declaration) {
        parent(assemblyElement)
        parent(moduleElement)
        parent(classElement)
    }

    val assembly: Element by element(Element.Category.Declaration) {
        parent(assemblyElement)
    }

    val assemblyReference: Element by element(Element.Category.Declaration) {
        parent(assemblyElement)

        field("name", type<String>())
        field("alias", type<String>(), nullable = true)
    }

    val corflags: Element by element(Element.Category.Declaration) {
        parent(assemblyElement)

        +field("value", type<Int>())
    }

    val file: Element by element(Element.Category.Declaration) {
        parent(assemblyElement)

        +field("hasMetadata", type<Boolean>())
        +field("name", type<String>())
        +field("hash", type<ByteArray>())
        +field("entrypoint", type<Boolean>())
    }

    val field: Element by element(Element.Category.Declaration) {
        parent(assemblyElement)
        parent(moduleElement)
        parent(classElement)
    }

    val method: Element by element(Element.Category.Declaration) {
        parent(assemblyElement)
        parent(moduleElement)
        parent(classElement)
    }

    val customAttribute: Element by element(Element.Category.Declaration) {
        parent(assemblyElement)
        parent(moduleElement)
        parent(classElement)
    }
}
