/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr

import org.jetbrains.kotlin.backend.common.ir.SharedVariablesManager
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.ir.IrBuiltIns
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrFactory
import org.jetbrains.kotlin.ir.declarations.IrVariable
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrGetValue
import org.jetbrains.kotlin.ir.expressions.IrSetValue
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol

class ClrSharedVariablesManager(
    module: ModuleDescriptor,
    val symbols: ClrSymbols,
    val irBuiltIns: IrBuiltIns,
    irFactory: IrFactory,
) : SharedVariablesManager {
    override fun declareSharedVariable(originalDeclaration: IrVariable): IrVariable {
        TODO("Not yet implemented")
    }

    override fun defineSharedValue(
        originalDeclaration: IrVariable,
        sharedVariableDeclaration: IrVariable,
    ): IrStatement {
        TODO("Not yet implemented")
    }

    override fun getSharedValue(
        sharedVariableSymbol: IrValueSymbol,
        originalGet: IrGetValue,
    ): IrExpression {
        TODO("Not yet implemented")
    }

    override fun setSharedValue(
        sharedVariableSymbol: IrValueSymbol,
        originalSet: IrSetValue,
    ): IrExpression {
        TODO("Not yet implemented")
    }
}
