/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator.model

import org.jetbrains.kotlin.generators.tree.*

sealed class Field(
    override val name: String,
    override var isMutable: Boolean,
) : AbstractField<Field>() {
    override var defaultValueInBuilder: String?
        get() = null
        set(_) = error("Builders are not supported")

    override var customSetter: String? = null

    override var isFinal: Boolean = false

    override fun updateFieldsInCopy(copy: Field) {
        super.updateFieldsInCopy(copy)
        copy.customSetter = customSetter
        copy.symbolFieldRole = symbolFieldRole
    }

    override fun toString() = "$name: $typeRef"
}

class SimpleField(
    name: String,
    override var typeRef: TypeRefWithNullability,
    mutable: Boolean,
    override val isChild: Boolean,
) : Field(name, mutable) {
    override fun substituteType(map: TypeParameterSubstitutionMap) {
        typeRef = typeRef.substitute(map) as TypeRefWithNullability
    }

    override fun internalCopy() = SimpleField(name, typeRef, isMutable, isChild)
}

class ListField(
    name: String,
    override var baseType: TypeRef,
    private val isNullable: Boolean,
    override val listType: ClassRef<PositionTypeParameterRef>,
    mutable: Boolean,
    override val isChild: Boolean,
) : Field(name, mutable),
    org.jetbrains.kotlin.generators.tree.ListField {
    override val typeRef: ClassRef<PositionTypeParameterRef>
        get() = listType.withArgs(baseType).copy(isNullable)

    override val containsElement: Boolean
        get() = (baseType as? ElementOrRef<*>)?.element is Element

    override fun substituteType(map: TypeParameterSubstitutionMap) {
        baseType = baseType.substitute(map)
    }

    override fun internalCopy() = ListField(name, baseType, isNullable, listType, isMutable, isChild)

    enum class Mutability {
        Var,
        MutableList,
        Array,
    }
}
