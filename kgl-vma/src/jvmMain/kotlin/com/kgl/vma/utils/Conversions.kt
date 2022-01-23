package com.kgl.vma.utils

import org.lwjgl.system.*

inline fun <T : Struct, BUFFER : StructBuffer<T, BUFFER>, reified S> BUFFER.mapToArray(size: Int, body: (T) -> S): Array<S> = Array(size) { body(get(it)) }
