/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.descriptors.clr

import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.descriptors.Visibility

object VisibilityAttributes {
    object NestedAssemblyVisibility : Visibility("nested_assembly", isPublicAPI = false) {
        override fun mustCheckInImports(): Boolean = true

        override fun normalize(): Visibility = Visibilities.Internal

        override val internalDisplayName: String
            get() = "public/*assembly*/"

        override val externalDisplayName: String
            get() = "assembly-private"
    }

    object NestedFamilyAndAssemblyVisibility : Visibility("nested_famandassem", isPublicAPI = false) {
        override fun mustCheckInImports(): Boolean = true

        override fun normalize(): Visibility = Visibilities.Internal

        override val internalDisplayName: String
            get() = "public/*family and assembly*/"

        override val externalDisplayName: String
            get() = "famandassem-private"
    }

    object NestedFamilyVisibility : Visibility("nested_family", isPublicAPI = false) {
        override fun mustCheckInImports(): Boolean = true

        override fun normalize(): Visibility = Visibilities.Internal

        override val internalDisplayName: String
            get() = "public/*family*/"

        override val externalDisplayName: String
            get() = "family-private"
    }

    object NestedFamilyOrAssemblyVisibility : Visibility("nested_famorassem", isPublicAPI = false) {
        override fun mustCheckInImports(): Boolean = true

        override fun normalize(): Visibility = Visibilities.Internal

        override val internalDisplayName: String
            get() = "public/*family or assembly*/"

        override val externalDisplayName: String
            get() = "famorassem-private"
    }

    object NestedPrivateVisibility : Visibility("nested_private", isPublicAPI = false) {
        override fun mustCheckInImports(): Boolean = true

        override fun normalize(): Visibility = Visibilities.Private

        override val internalDisplayName: String
            get() = "private"

        override val externalDisplayName: String
            get() = "private"
    }

    object NestedPublicVisibility : Visibility("nested_public", isPublicAPI = true) {
        override fun mustCheckInImports(): Boolean = true

        override fun normalize(): Visibility = Visibilities.Public

        override val internalDisplayName: String
            get() = "public"

        override val externalDisplayName: String
            get() = "public"
    }
}