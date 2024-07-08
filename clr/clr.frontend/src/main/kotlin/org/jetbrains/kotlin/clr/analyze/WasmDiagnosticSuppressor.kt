/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.clr.analyze

import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor
import org.jetbrains.kotlin.resolve.checkers.PlatformDiagnosticSuppressor

object WasmDiagnosticSuppressor : PlatformDiagnosticSuppressor {
    override fun shouldReportNoBody(descriptor: CallableMemberDescriptor): Boolean = true
}
