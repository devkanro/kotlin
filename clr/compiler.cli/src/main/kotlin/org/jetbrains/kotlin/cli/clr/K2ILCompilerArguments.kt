/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.cli.clr

import org.jetbrains.kotlin.cli.common.arguments.Argument
import org.jetbrains.kotlin.cli.common.arguments.CommonKlibBasedCompilerArguments

class K2ILCompilerArguments : CommonKlibBasedCompilerArguments() {
    @Argument(
        value = "-target-framework",
        valueDescription = "<version>",
        description = "The target framework of the targeted .NET Runtime (net40, net47, net472, net48, ..., net8.0), with 'net8.0' as the default.",
    )
    var targetFramework: String? = null
        set(value) {
            checkFrozen()
            field = if (value.isNullOrEmpty()) null else value
        }

    @Argument(
        value = "-assembly-name",
        valueDescription = "<string>",
        description = "The result assembly name",
    )
    var assemblyName: String? = null
        set(value) {
            checkFrozen()
            field = if (value.isNullOrEmpty()) null else value
        }
}