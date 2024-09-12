/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.instructions

enum class ILOpCodeControlFlow {
    /**
     * unconditional branch
     */
    BRANCH,

    /**
     * method call
     */
    CALL,

    /**
     * conditional branch
     */
    COND_BRANCH,

    /**
     * unused operation or prefix code
     */
    META,

    /**
     * control flow unaltered (“fall through”)
     */
    NEXT,

    /**
     * return from method
     */
    RETURN,

    /**
     * throw or rethrow an exception
     */
    THROW,
}
