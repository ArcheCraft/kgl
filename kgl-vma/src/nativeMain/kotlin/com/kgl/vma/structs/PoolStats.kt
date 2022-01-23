package com.kgl.vma.structs

import cvma.*

fun PoolStats.Companion.from(pointer: VmaPoolStats) = PoolStats(
	pointer.size,
	pointer.unusedSize,
	pointer.allocationCount,
	pointer.unusedRangeCount,
	pointer.unusedRangeSizeMax,
	pointer.blockCount
)
