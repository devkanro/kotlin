/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr.lower

import org.jetbrains.kotlin.backend.common.phaser.CompilerPhase
import org.jetbrains.kotlin.backend.common.phaser.DEFAULT_IR_ACTIONS
import org.jetbrains.kotlin.backend.common.phaser.SameTypeNamedCompilerPhase
import org.jetbrains.kotlin.backend.common.phaser.then
import org.jetbrains.kotlin.ir.backend.clr.ClrBackendContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment

private fun List<CompilerPhase<ClrBackendContext, IrModuleFragment, IrModuleFragment>>.toCompilerPhase() =
    reduce { acc, lowering -> acc.then(lowering) }

val loweringList = listOf<CompilerPhase<ClrBackendContext, IrModuleFragment, IrModuleFragment>>(
)

val clrPhases = SameTypeNamedCompilerPhase(
    name = "IrModuleLowering",
    description = "IR module lowering",
    lower = loweringList.toCompilerPhase(),
    actions = DEFAULT_IR_ACTIONS,
    nlevels = 1
)