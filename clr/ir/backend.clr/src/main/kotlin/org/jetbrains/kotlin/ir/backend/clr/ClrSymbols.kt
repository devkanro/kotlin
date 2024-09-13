/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.clr

import org.jetbrains.kotlin.backend.common.ir.Symbols
import org.jetbrains.kotlin.backend.jvm.functionByName
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.ir.builders.declarations.addConstructor
import org.jetbrains.kotlin.ir.builders.declarations.addFunction
import org.jetbrains.kotlin.ir.builders.declarations.addValueParameter
import org.jetbrains.kotlin.ir.builders.declarations.buildClass
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrPackageFragment
import org.jetbrains.kotlin.ir.declarations.createEmptyExternalPackageFragment
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.types.makeNullable
import org.jetbrains.kotlin.ir.types.starProjectedType
import org.jetbrains.kotlin.ir.util.SymbolTable
import org.jetbrains.kotlin.ir.util.createImplicitParameterDeclarationWithWrappedDescriptor
import org.jetbrains.kotlin.ir.util.defaultType
import org.jetbrains.kotlin.ir.util.functions
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

class ClrSymbols(
    private val context: ClrBackendContext,
    symbolTable: SymbolTable,
) : Symbols(context.irBuiltIns, symbolTable) {
    private val irFactory = context.irFactory

    private val kotlinPackage: IrPackageFragment = createPackage(FqName("kotlin"))
    private val kotlinCoroutinesPackage: IrPackageFragment = createPackage(FqName("kotlin.coroutines"))
    private val kotlinCoroutinesClrInternalPackage: IrPackageFragment = createPackage(FqName("kotlin.coroutines.clr.internal"))
    private val kotlinClrPackage: IrPackageFragment = createPackage(FqName("kotlin.clr"))
    private val kotlinClrInternalPackage: IrPackageFragment = createPackage(FqName("kotlin.clr.internal"))
    private val kotlinClrFunctionsPackage: IrPackageFragment = createPackage(FqName("kotlin.clr.functions"))
    private val kotlinEnumsPackage: IrPackageFragment = createPackage(FqName("kotlin.enums"))
    private val kotlinReflectPackage: IrPackageFragment = createPackage(FqName("kotlin.reflect"))
    private val systemPackage: IrPackageFragment = createPackage(FqName("system"))
    private val systemTextPackage: IrPackageFragment = createPackage(FqName("system.text"))
    private val kotlinInternalPackage: IrPackageFragment = createPackage(FqName("kotlin.internal"))

    private fun createPackage(fqName: FqName): IrPackageFragment = createEmptyExternalPackageFragment(context.state.module, fqName)

    private fun createClass(
        fqName: FqName,
        classKind: ClassKind = ClassKind.CLASS,
        classModality: Modality = Modality.FINAL,
        classIsValue: Boolean = false,
        block: (IrClass) -> Unit = {},
    ): IrClassSymbol =
        irFactory
            .buildClass {
                name = fqName.shortName()
                kind = classKind
                modality = classModality
                isValue = classIsValue
            }.apply {
                parent =
                    when (fqName.parent().asString()) {
                        "kotlin" -> kotlinPackage
                        "kotlin.coroutines" -> kotlinCoroutinesPackage
                        "kotlin.coroutines.clr.internal" -> kotlinCoroutinesClrInternalPackage
                        "kotlin.enums" -> kotlinEnumsPackage
                        "kotlin.clr.internal" -> kotlinClrInternalPackage
                        "kotlin.clr.functions" -> kotlinClrFunctionsPackage
                        "kotlin.clr" -> kotlinClrPackage
                        "kotlin.reflect" -> kotlinReflectPackage
                        "system" -> systemPackage
                        "system.text" -> systemTextPackage
                        "kotlin.internal" -> kotlinInternalPackage
                        else -> error("Other packages are not supported yet: $fqName")
                    }
                createImplicitParameterDeclarationWithWrappedDescriptor()
                block(this)
            }.symbol

    private val intrinsicsClass: IrClassSymbol =
        createClass(
            FqName("kotlin.clr.internal.Intrinsics"),
        ) { klass ->
            klass.addFunction("throwNullPointerException", irBuiltIns.nothingType, isStatic = true).apply {
                addValueParameter("message", irBuiltIns.stringType)
            }
            klass.addFunction("throwTypeCastException", irBuiltIns.nothingType, isStatic = true).apply {
                addValueParameter("message", irBuiltIns.stringType)
            }
            klass.addFunction("throwIllegalAccessException", irBuiltIns.nothingType, isStatic = true).apply {
                addValueParameter("message", irBuiltIns.stringType)
            }
            klass.addFunction("throwUnsupportedOperationException", irBuiltIns.nothingType, isStatic = true).apply {
                addValueParameter("message", irBuiltIns.stringType)
            }
            klass.addFunction("throwUninitializedPropertyAccessException", irBuiltIns.unitType, isStatic = true).apply {
                addValueParameter("propertyName", irBuiltIns.stringType)
            }
            klass.addFunction("throwKotlinNothingValueException", irBuiltIns.nothingType, isStatic = true)
            klass.addFunction("checkExpressionValueIsNotNull", irBuiltIns.unitType, isStatic = true).apply {
                addValueParameter("value", irBuiltIns.anyNType)
                addValueParameter("expression", irBuiltIns.stringType)
            }
            klass.addFunction("checkNotNullExpressionValue", irBuiltIns.unitType, isStatic = true).apply {
                addValueParameter("value", irBuiltIns.anyNType)
                addValueParameter("expression", irBuiltIns.stringType)
            }
            klass.addFunction("stringPlus", irBuiltIns.stringType, isStatic = true).apply {
                addValueParameter("self", irBuiltIns.stringType.makeNullable())
                addValueParameter("other", irBuiltIns.anyNType)
            }
            klass.addFunction("checkNotNull", irBuiltIns.unitType, isStatic = true).apply {
                addValueParameter("object", irBuiltIns.anyNType)
            }
            klass.addFunction("checkNotNull", irBuiltIns.unitType, isStatic = true).apply {
                addValueParameter("object", irBuiltIns.anyNType)
                addValueParameter("message", irBuiltIns.stringType)
            }
            klass.addFunction("throwNpe", irBuiltIns.unitType, isStatic = true)
            klass.addFunction("singleArgumentInlineFunction", irBuiltIns.unitType, isStatic = true, isInline = true).apply {
                addValueParameter("arg", irBuiltIns.functionClass.defaultType)
            }

            klass.declarations.add(
                irFactory
                    .buildClass {
                        name = Name.identifier("Kotlin")
                    }.apply {
                        parent = klass
                        createImplicitParameterDeclarationWithWrappedDescriptor()
                    },
            )
        }

    override val throwNullPointerException: IrSimpleFunctionSymbol =
        intrinsicsClass.functions.single { it.owner.name.asString() == "throwNullPointerException" }

    override val throwTypeCastException: IrSimpleFunctionSymbol =
        intrinsicsClass.functions.single { it.owner.name.asString() == "throwTypeCastException" }

    override val throwUninitializedPropertyAccessException: IrSimpleFunctionSymbol =
        intrinsicsClass.functions.single { it.owner.name.asString() == "throwUninitializedPropertyAccessException" }

    override val throwKotlinNothingValueException: IrSimpleFunctionSymbol =
        intrinsicsClass.functions.single { it.owner.name.asString() == "throwKotlinNothingValueException" }

    override val stringBuilder: IrClassSymbol =
        createClass(FqName("system.text.StringBuilder")) { klass ->
            klass.addConstructor()
            klass.addFunction("toString", irBuiltIns.stringType).apply {
                overriddenSymbols = overriddenSymbols + any.functionByName("toString")
            }

            val appendTypes =
                with(irBuiltIns) {
                    listOf(
                        anyNType,
                        stringType.makeNullable(),
                        booleanType,
                        charType,
                        intType,
                        longType,
                        floatType,
                        doubleType,
                    )
                }
            for (type in appendTypes) {
                klass.addFunction("append", klass.defaultType).apply {
                    addValueParameter("value", type)
                }
            }
        }

    override val defaultConstructorMarker: IrClassSymbol =
        createClass(FqName("kotlin.clr.internal.DefaultConstructorMarker"))

    override val coroutineImpl: IrClassSymbol
        get() = error("not implemented")

    override val coroutineSuspendedGetter: IrSimpleFunctionSymbol
        get() = error("not implemented")

    override val getContinuation: IrSimpleFunctionSymbol
        get() = error("not implemented")

    override val coroutineContextGetter: IrSimpleFunctionSymbol
        get() = error("not implemented")

    override val suspendCoroutineUninterceptedOrReturn: IrSimpleFunctionSymbol
        get() = error("not implemented")

    override val coroutineGetContext: IrSimpleFunctionSymbol
        get() = error("not implemented")

    override val returnIfSuspended: IrSimpleFunctionSymbol
        get() = error("not implemented")

    override val continuationClass: IrClassSymbol
        get() = error("not implemented")

    override val functionAdapter: IrClassSymbol =
        createClass(FqName("kotlin.clr.internal.FunctionAdapter"), ClassKind.INTERFACE) { klass ->
            klass.addFunction("getFunctionDelegate", irBuiltIns.functionClass.starProjectedType, Modality.ABSTRACT)
        }
}
