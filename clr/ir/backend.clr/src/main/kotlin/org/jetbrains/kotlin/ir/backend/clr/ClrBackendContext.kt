/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr

import org.jetbrains.kotlin.backend.common.CommonBackendContext
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.ir.Ir
import org.jetbrains.kotlin.backend.common.lower.InnerClassesSupport
import org.jetbrains.kotlin.backend.common.phaser.PhaseConfig
import org.jetbrains.kotlin.backend.jvm.*
import org.jetbrains.kotlin.codegen.state.GenerationState
import org.jetbrains.kotlin.ir.IrBuiltIns
import org.jetbrains.kotlin.ir.declarations.IrFactory
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl
import org.jetbrains.kotlin.ir.linkage.IrProvider
import org.jetbrains.kotlin.ir.types.IrTypeSystemContext
import org.jetbrains.kotlin.ir.util.SymbolTable

class ClrBackendContext(
    val state: GenerationState,
    override val irBuiltIns: IrBuiltIns,
    val symbolTable: SymbolTable,
    val phaseConfig: PhaseConfig,
    val generatorExtensions: JvmGeneratorExtensions,
    val backendExtension: JvmBackendExtension,
    val irSerializer: JvmIrSerializer?,
    val irDeserializer: JvmIrDeserializer,
    val irProviders: List<IrProvider>,
    val irPluginContext: IrPluginContext?,
) : CommonBackendContext {
    override val irFactory: IrFactory = IrFactoryImpl

    override val scriptMode: Boolean = false

    override val builtIns = state.module.builtIns

    override val typeSystem: IrTypeSystemContext = ClrIrTypeSystemContext(irBuiltIns)

    override val innerClassesSupport: InnerClassesSupport = ClrInnerClassesSupport(irFactory)

    inner class ClrIr(
        symbolTable: SymbolTable,
    ) : Ir<ClrBackendContext>(this) {
        override val symbols = ClrSymbols(this@ClrBackendContext, symbolTable)

        override fun shouldGenerateHandlerParameterForDefaultBodyFun() = true
    }

    override val ir = ClrIr(this.symbolTable)

    override val sharedVariablesManager = JvmSharedVariablesManager(state.module, ir.symbols, irBuiltIns, irFactory)

}
