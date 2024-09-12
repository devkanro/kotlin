/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator

import org.jetbrains.kotlin.generators.tree.TypeKind
import org.jetbrains.kotlin.generators.tree.type
import org.jetbrains.kotlin.il.generator.Packages.clrDescriptors
import org.jetbrains.kotlin.il.generator.Packages.instructions
import org.jetbrains.kotlin.il.generator.Packages.primitive
import org.jetbrains.kotlin.il.generator.Packages.tree
import org.jetbrains.kotlin.il.generator.Packages.types

object Packages {
    const val tree = "org.jetbrains.kotlin.il"
    const val exprs = "org.jetbrains.kotlin.il.expressions"
    const val declarations = "org.jetbrains.kotlin.il.declarations"
    const val types = "org.jetbrains.kotlin.il.types"
    const val instructions = "org.jetbrains.kotlin.il.instructions"
    const val visitors = "org.jetbrains.kotlin.il.visitors"
    const val primitive = "org.jetbrains.kotlin.il.primitive"
    const val descriptors = "org.jetbrains.kotlin.descriptors"
    const val clrDescriptors = "org.jetbrains.kotlin.descriptors.clr"
    const val util = "org.jetbrains.kotlin.il.util"
}

val elementBaseType = type(tree, "ILElementBase", TypeKind.Class)

val type = type(types, "ILType", TypeKind.Interface)

val nativeType = type(types, "ILNativeType", TypeKind.Interface)

val classVisibilityType = type(primitive, "ILClassVisibility", TypeKind.Interface)

val memberVisibilityType = type(primitive, "ILMemberVisibility", TypeKind.Interface)

val fieldContractKindType = type(primitive, "ILMethodContractKind", TypeKind.Interface)

val methodContractKindType = type(primitive, "ILFieldContractKind", TypeKind.Interface)

val platformInvokeKind = type(primitive, "ILPlatformInvokeKind", TypeKind.Interface)

val callKindType = type(primitive, "ILCallKind", TypeKind.Interface)

val eventKindType = type(primitive, "ILEventMethodKind", TypeKind.Interface)

val propertyKindType = type(primitive, "ILEventPropertyKind", TypeKind.Interface)

val methodImplAttrKind = type(primitive, "ILMethodImplAttrKind", TypeKind.Interface)

val opCodeType = type(instructions, "ILOpCodeType", TypeKind.Interface)

val opCodeInputType = type(instructions, "ILOpCodeInput", TypeKind.Interface)

val opCodeOutputType = type(instructions, "ILOpCodeOutput", TypeKind.Interface)

val opCodeInputParam = type(instructions, "ILOpCodeParam", TypeKind.Interface)

val opCodeControlFlow = type(instructions, "ILOpCodeControlFlow", TypeKind.Interface)

val implementationDetailAnnotationType = type(tree, "ILImplementationDetail", TypeKind.Class)

val layoutAttributeType = type(clrDescriptors, "LayoutAttribute", TypeKind.Class)
