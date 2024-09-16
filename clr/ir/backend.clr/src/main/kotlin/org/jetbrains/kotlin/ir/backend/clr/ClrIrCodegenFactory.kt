/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr

import org.jetbrains.kotlin.backend.common.phaser.PhaseConfig
import org.jetbrains.kotlin.codegen.CodegenFactory
import org.jetbrains.kotlin.codegen.state.GenerationState
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.ir.util.SymbolTable
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi2ir.generators.fragments.EvaluatorFragmentInfo

class ClrIrCodegenFactory(
    configuration: CompilerConfiguration,
    private val phaseConfig: PhaseConfig?,
    private val externalSymbolTable: SymbolTable? = null,
    private val evaluatorFragmentInfoForPsi2Ir: EvaluatorFragmentInfo? = null,
) : CodegenFactory {
    data class ClrIrBackendInput(
        val test: String,
    ) : CodegenFactory.BackendInput

    private data class ClrIrCodegenInput(
        override val state: GenerationState,
    ) : CodegenFactory.CodegenInput

    override fun convertToIr(input: CodegenFactory.IrConversionInput): CodegenFactory.BackendInput = ClrIrBackendInput("test")

    override fun getModuleChunkBackendInput(
        wholeBackendInput: CodegenFactory.BackendInput,
        sourceFiles: Collection<KtFile>,
    ): CodegenFactory.BackendInput = wholeBackendInput

    override fun invokeLowerings(
        state: GenerationState,
        input: CodegenFactory.BackendInput,
    ): CodegenFactory.CodegenInput = ClrIrCodegenInput(state)

    override fun invokeCodegen(input: CodegenFactory.CodegenInput) {
    }
}
