/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator

import org.jetbrains.kotlin.descriptors.Visibility
import org.jetbrains.kotlin.generators.tree.type
import org.jetbrains.kotlin.il.generator.config.AbstractILTreeBuilder
import org.jetbrains.kotlin.il.generator.model.Element
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

        fun offsetField(prefix: String) =
            field(prefix + "Offset", int, mutable = false) {
                kDoc =
                    """
                    The $prefix offset of the syntax node from which this IL node was generated,
                    in number of characters from the start of the source file. If there is no source information for this IL node,
                    the [UNDEFINED_OFFSET] constant is used. In order to get the line number and the column number from this offset,
                    [ILFileEntry.getLineNumber] and [ILFileEntry.getColumnNumber] can be used.
                    
                    @see ILFileEntry.getSourceRangeInfo
                    """.trimIndent()
            }

        +offsetField("start")
        +offsetField("end")

        kDoc = "The root interface of the IL tree. Each IL node implements this interface."
    }

    val declaration: Element by element(Element.Category.Declaration) {
        +descriptor("DeclarationDescriptor")
    }

    val `class`: Element by element(Element.Category.Declaration) {
        parent(declaration)

        +field("visibility", type<Visibility>())
        +field("layout", layoutAttributeType)
    }
}
