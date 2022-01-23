package com.kgl.vma.utils

import kotlinx.cinterop.*

inline fun <reified T : CVariable, ARRAY : CArrayPointer<T>, reified S> ARRAY.mapToArray(size: Int, body: (T) -> S): Array<S> = Array(size) { body(get(it)) }
