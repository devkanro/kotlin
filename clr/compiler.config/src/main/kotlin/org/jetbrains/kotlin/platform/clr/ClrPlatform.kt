/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.platform.clr

import org.jetbrains.kotlin.platform.SimplePlatform
import org.jetbrains.kotlin.platform.TargetPlatformVersion

abstract class ClrPlatform : SimplePlatform("CLR")

abstract class DotNetPlatform : ClrPlatform()

class DotNetVersion(override val description: String) : TargetPlatformVersion {
    override fun equals(other: Any?): Boolean {
        
    }
}
