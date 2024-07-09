/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.annotations.clr

/**
 * Controls the layout of an object when exported to unmanaged code.
 */
enum class LayoutKind(val value: Int) {
    /**
     * The runtime automatically chooses an appropriate layout for
     * the members of an object in unmanaged memory.
     * Objects defined with this enumeration member cannot be
     * exposed outside of managed code.
     * Attempting to do so generates an exception.
     */
    Auto(3),

    /**
     * The precise position of each member of an object in unmanaged
     * memory is explicitly controlled, subject to the setting of
     * the Pack field.
     * Each member must use the [FieldOffset] to indicate
     * the position of that field within the type.
     */
    Explicit(2),

    /**
     * The members of the object are laid out sequentially,
     * in the order in which they appear when exported to unmanaged memory.
     * The members are laid out according to the packing specified in Pack,
     * and can be noncontiguous.
     */
    Sequential(0),
}