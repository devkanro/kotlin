/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr.lower

import org.jetbrains.kotlin.backend.common.ClassLoweringPass
import org.jetbrains.kotlin.backend.common.lower.createIrBuilder
import org.jetbrains.kotlin.backend.common.phaser.PhaseDescription
import org.jetbrains.kotlin.ir.backend.clr.ClrBackendContext
import org.jetbrains.kotlin.ir.builders.declarations.buildField
import org.jetbrains.kotlin.ir.builders.irCall
import org.jetbrains.kotlin.ir.builders.irExprBody
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrDeclarationContainer
import org.jetbrains.kotlin.ir.declarations.IrField
import org.jetbrains.kotlin.ir.irAttribute
import org.jetbrains.kotlin.ir.util.constructors
import org.jetbrains.kotlin.ir.util.defaultType
import org.jetbrains.kotlin.ir.util.isObject
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.utils.addToStdlib.getOrSetIfNull

@PhaseDescription(
    name = "ObjectClass",
    description = "Handle object classes",
)
internal class ObjectClassLowering(
    val context: ClrBackendContext,
) : ClassLoweringPass {
    private val pendingTransformations = mutableListOf<Function0<Unit>>()

    override fun lower(irClass: IrClass) {
        if (!irClass.isObject) return

        val constructor =
            irClass.constructors.find { it.isPrimary } ?: throw AssertionError("Object should have a primary constructor: ${irClass.name}")

        val instanceField = irClass.instanceField()

        with(context.createIrBuilder(instanceField.symbol)) {
            instanceField.initializer = irExprBody(irCall(constructor.symbol))
        }

        pendingTransformations.add {
            (instanceField.parent as IrDeclarationContainer).declarations.add(0, instanceField)
        }
    }

    private fun IrClass.instanceField(): IrField =
        ::fieldForObjectInstance.getOrSetIfNull {
            factory.buildField {
                name = Name.identifier("Instance")
                type = this@instanceField.defaultType
                isFinal = true
                isStatic = true
                visibility = this@instanceField.visibility
                parent = this@instanceField
            }
        }
}

private var IrClass.fieldForObjectInstance: IrField? by irAttribute(followAttributeOwner = false)
