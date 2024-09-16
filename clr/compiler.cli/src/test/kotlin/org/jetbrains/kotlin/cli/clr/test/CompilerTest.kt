/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.cli.clr.test

import org.jetbrains.kotlin.cli.clr.K2ILCompiler
import org.jetbrains.kotlin.cli.common.CLICompiler
import org.junit.Test

class CompilerTest {
    @Test
    fun testCompiler() {
        CLICompiler.doMain(K2ILCompiler(), arrayOf("src/test/resource/test.kt"))
    }
}
