/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr

import org.jetbrains.kotlin.backend.common.ModuleLoweringPass
import org.jetbrains.kotlin.backend.common.phaser.*
import org.jetbrains.kotlin.ir.backend.clr.lower.ClrPropertiesLowering
import org.jetbrains.kotlin.ir.backend.clr.lower.FileClassLowering
import org.jetbrains.kotlin.ir.backend.clr.lower.ObjectClassLowering
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment

private typealias ClrPhase = CompilerPhase<ClrBackendContext, IrModuleFragment, IrModuleFragment>

private val clrFilePhases =
    createFilePhases(
        ::ClrPropertiesLowering,
        ::ObjectClassLowering,
    )

val clrPhases =
    SameTypeNamedCompilerPhase(
        name = "IrModuleLowering",
        description = "IR module lowering",
        lower =
            buildModuleLoweringsPhase(
                ::FileClassLowering,
            ).then(
                performByIrFile(
                    name = "PerformByIrFile",
                    description = "Perform phases by IrFile",
                    lower = clrFilePhases,
                    supportParallel = false,
                ),
            ),
        actions = DEFAULT_IR_ACTIONS,
        nlevels = 1,
    )

private fun buildModuleLoweringsPhase(vararg phases: (ClrBackendContext) -> ModuleLoweringPass): ClrPhase =
    createModulePhases(*phases).reduce(ClrPhase::then)
