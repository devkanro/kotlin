/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.descriptors.clr

abstract class InheritanceAttribute(
    val name: String,
) {
    override fun toString(): String = "InheritanceAttribute($name)"

    override fun hashCode(): Int = name.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other !is LayoutAttribute) return false

        return other.name == name
    }
}
