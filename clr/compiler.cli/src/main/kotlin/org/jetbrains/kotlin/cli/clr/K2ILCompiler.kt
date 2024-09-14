/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.cli.clr

import com.intellij.openapi.Disposable
import org.jetbrains.kotlin.cli.common.CLICompiler
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.CommonCompilerPerformanceManager
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.common.ExitCode.COMPILATION_ERROR
import org.jetbrains.kotlin.cli.common.arguments.*
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity.ERROR
import org.jetbrains.kotlin.config.CommonConfigurationKeys
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.JvmTarget
import org.jetbrains.kotlin.config.Services
import org.jetbrains.kotlin.utils.KotlinPaths

class K2ILCompiler : CLICompiler<K2ILCompilerArguments>() {
    class K2ILCompilerPerformanceManager : CommonCompilerPerformanceManager("Kotlin to IL Compiler")

    override val defaultPerformanceManager: CommonCompilerPerformanceManager = K2ILCompilerPerformanceManager()

    override fun createArguments(): K2ILCompilerArguments {
        return K2ILCompilerArguments()
    }

    override fun doExecute(
        arguments: K2ILCompilerArguments,
        configuration: CompilerConfiguration,
        rootDisposable: Disposable,
        paths: KotlinPaths?,
    ): ExitCode {
        val messageCollector = configuration.getNotNull(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY)
        val performanceManager = configuration[CLIConfigurationKeys.PERF_MANAGER]

        if (arguments.script) {
            messageCollector.report(ERROR, "K/CLR does not support Kotlin script (*.kts) files")
            return COMPILATION_ERROR
        }

        arguments

    }

    override fun setupPlatformSpecificArgumentsAndServices(
        configuration: CompilerConfiguration,
        arguments: K2ILCompilerArguments,
        services: Services
    ) {

    }
}

class K2ILCompilerArguments : CommonKlibBasedCompilerArguments() {
    @Argument(
        value = "-target-framework",
        valueDescription = "<version>",
        description = "The target framework of the generated JVM bytecode (${JvmTarget.SUPPORTED_VERSIONS_DESCRIPTION}), with 1.8 as the default.",
    )
    var targetFramework: String? = null
        set(value) {
            checkFrozen()
            field = if (value.isNullOrEmpty()) null else value
        }
}