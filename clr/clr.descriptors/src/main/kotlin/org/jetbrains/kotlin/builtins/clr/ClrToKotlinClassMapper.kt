/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.builtins.clr

import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.builtins.PlatformToKotlinClassMapper
import org.jetbrains.kotlin.builtins.StandardNames
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.resolve.DescriptorUtils
import org.jetbrains.kotlin.resolve.descriptorUtil.builtIns
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameUnsafe

object ClrToKotlinClassMapper: PlatformToKotlinClassMapper {
    override fun mapPlatformClass(classDescriptor: ClassDescriptor): MutableCollection<ClassDescriptor> {
        val className = DescriptorUtils.getFqName(classDescriptor)
        return if (className.isSafe)
            mapPlatformClass(className.toSafe(), classDescriptor.builtIns)
        else
            emptySet()
    }

    fun mapPlatformClass(fqName: FqName, builtIns: KotlinBuiltIns): Collection<ClassDescriptor> {
        val kotlinAnalog = mapClrToKotlin(fqName, builtIns) ?: return emptySet()

        val kotlinMutableAnalogFqName = JavaToKotlinClassMap.readOnlyToMutable(kotlinAnalog.fqNameUnsafe) ?: return setOf(kotlinAnalog)

        return listOf(kotlinAnalog, builtIns.getBuiltInClassByFqName(kotlinMutableAnalogFqName))
    }

    fun mapClrToKotlin(fqName: FqName, builtIns: KotlinBuiltIns, functionTypeArity: Int? = null): ClassDescriptor? {
        val kotlinClassId =
            if (functionTypeArity != null && fqName == JavaToKotlinClassMap.FUNCTION_N_FQ_NAME) StandardNames.getFunctionClassId(functionTypeArity)
            else JavaToKotlinClassMap.mapJavaToKotlin(fqName)
        return if (kotlinClassId != null) builtIns.getBuiltInClassByFqName(kotlinClassId.asSingleFqName()) else null
    }
}