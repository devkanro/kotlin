/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.cli.clr

import org.jetbrains.kotlin.cli.common.arguments.Argument
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments

class K2ILCompilerArguments : CommonCompilerArguments() {
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
        valueDescription = "<name>",
        description = "The result assembly name",
    )
    var assemblyName: String? = null
        set(value) {
            checkFrozen()
            field = if (value.isNullOrEmpty()) null else value
        }

    @Argument(
        value = "-target",
        valueDescription = "<target>",
        description = "Specify the type of the output assembly (library, exe, module, winexe, winmdobj).",
    )
    var target: String? = null
        set(value) {
            checkFrozen()
            field = if (value.isNullOrEmpty()) null else value
        }

    @Argument(
        value = "-platform-target",
        valueDescription = "<target>",
        description = "Specifies which version of the CLR can run the assembly(anycpu, anycpu32bitpreferred, ARM, ARM64, x64, x86).",
    )
    var platformTarget: String? = null
        set(value) {
            checkFrozen()
            field = if (value.isNullOrEmpty()) null else value
        }

    @Argument(
        value = "-il-output",
        valueDescription = "<path>",
        description = "Specifies the transformed IL code output.",
    )
    var ilOutput: String? = null
        set(value) {
            checkFrozen()
            field = if (value.isNullOrEmpty()) null else value
        }

    @Argument(
        value = "-references",
        valueDescription = "<path>",
        description = "Reference metadata from the specified assembly file or files.",
    )
    var references: Array<String>? = null
        set(value) {
            checkFrozen()
            field = if (value.isNullOrEmpty()) null else value
        }
}
