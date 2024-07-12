/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator.config

import org.jetbrains.kotlin.generators.tree.config.AbstractImplementationConfigurator
import org.jetbrains.kotlin.il.generator.model.Element
import org.jetbrains.kotlin.il.generator.model.Field
import org.jetbrains.kotlin.il.generator.model.Implementation

abstract class AbstractILTreeImplementationConfigurator : AbstractImplementationConfigurator<Implementation, Element, Field>() {
    final override fun createImplementation(
        element: Element,
        name: String?,
    ) = Implementation(element, name)
}
