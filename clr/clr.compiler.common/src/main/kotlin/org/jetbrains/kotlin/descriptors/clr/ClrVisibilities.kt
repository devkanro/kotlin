/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.descriptors.clr

import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.descriptors.Visibility

object ClrVisibilities {
    object NestedAssemblyVisibility : Visibility("nested_assembly", isPublicAPI = false) {
        override fun mustCheckInImports(): Boolean {
            TODO("Not yet implemented")
        }

        override fun normalize(): Visibility {
            return Visibilities.Protected
        }

        override val internalDisplayName: String
            get() = "public/*assembly*/"

        override val externalDisplayName: String
            get() = "assembly-private"
    }
}