/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator

import org.jetbrains.kotlin.il.generator.config.AbstractILTreeImplementationConfigurator

object ImplementationConfigurator : AbstractILTreeImplementationConfigurator() {
    override fun configure(model: Model) =
        with(ILTree) {
        }

    override fun configureAllImplementations(model: Model) {
    }
}
