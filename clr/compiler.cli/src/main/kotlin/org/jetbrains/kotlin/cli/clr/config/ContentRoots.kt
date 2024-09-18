/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.cli.clr.config

import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.config.ContentRoot
import org.jetbrains.kotlin.config.CompilerConfiguration
import java.io.File

data class CSharpSourceRoot(
    val path: String,
) : ContentRoot

data class ClrAssemblyRoot(
    val path: File,
) : ContentRoot

fun CompilerConfiguration.addClrAssemblyRoot(path: String) {
    add(CLIConfigurationKeys.CONTENT_ROOTS, ClrAssemblyRoot(File(path)))
}
