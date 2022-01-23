package com.kgl.vma.structs

import com.kgl.vma.utils.*
import cvma.*

fun Stats.Companion.from(pointer: VmaStats) = Stats(
	StatInfo.from(pointer.total),
	pointer.memoryType.mapToArray(VK_MAX_MEMORY_TYPES) { StatInfo.from(it) },
	pointer.memoryHeap.mapToArray(VK_MAX_MEMORY_HEAPS) { StatInfo.from(it) }
)
