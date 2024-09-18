/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.cli.clr

import com.intellij.openapi.Disposable
import org.jetbrains.kotlin.cli.clr.config.addClrAssemblyRoot
import org.jetbrains.kotlin.cli.common.*
import org.jetbrains.kotlin.cli.common.ExitCode.COMPILATION_ERROR
import org.jetbrains.kotlin.cli.common.ExitCode.OK
import org.jetbrains.kotlin.cli.common.config.addKotlinSourceRoot
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity.ERROR
import org.jetbrains.kotlin.cli.common.modules.ModuleBuilder
import org.jetbrains.kotlin.config.CommonConfigurationKeys
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.Services
import org.jetbrains.kotlin.fir.FirModuleDataImpl
import org.jetbrains.kotlin.fir.session.FirClrSessionFactory
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMetadataVersion
import org.jetbrains.kotlin.utils.KotlinPaths

class ClrMetadataVersion(
    versionArray: IntArray,
) : BinaryVersion(*versionArray) {
    override fun isCompatibleWithCurrentCompilerVersion(): Boolean = isCompatibleTo(INSTANCE)

    companion object {
        @JvmField
        val INSTANCE = JvmMetadataVersion(0, 0, 0)
    }
}

class K2ILCompiler : CLICompiler<K2ILCompilerArguments>() {
    class K2ILCompilerPerformanceManager : CommonCompilerPerformanceManager("Kotlin to IL Compiler")

    override val defaultPerformanceManager: CommonCompilerPerformanceManager = K2ILCompilerPerformanceManager()

    override fun createMetadataVersion(versionArray: IntArray): BinaryVersion = ClrMetadataVersion(versionArray)

    override fun MutableList<String>.addPlatformOptions(arguments: K2ILCompilerArguments) {
    }

    override fun createArguments(): K2ILCompilerArguments = K2ILCompilerArguments()

    override fun executableScriptFileName(): String = "kotlinc-clr"

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

        if (arguments.freeArgs.isEmpty() && !(incrementalCompilationIsEnabledForJs(arguments))) {
            if (arguments.version) {
                return OK
            }

            messageCollector.report(ERROR, "Specify at least one source file or directory", null)
            return COMPILATION_ERROR
        }

        val commonSourcesArray = arguments.commonSources
        val commonSources = commonSourcesArray?.toSet() ?: emptySet()

        for (arg in arguments.freeArgs) {
            configuration.addKotlinSourceRoot(arg, commonSources.contains(arg))
        }

        for (reference in arguments.references ?: arrayOf()) {
            configuration.addClrAssemblyRoot(reference)
        }

        FirModuleDataImpl
        FirClrSessionFactory.createModuleBaseSession()

        return OK
    }

    override fun setupPlatformSpecificArgumentsAndServices(
        configuration: CompilerConfiguration,
        arguments: K2ILCompilerArguments,
        services: Services,
    ) {
    }
}
