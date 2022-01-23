package com.kgl.vma.structs

import org.lwjgl.util.vma.*

fun PoolStats.Companion.from(pointer: VmaPoolStats) = PoolStats(
	pointer.size().toULong(),
	pointer.unusedSize().toULong(),
	pointer.allocationCount().toULong(),
	pointer.unusedRangeCount().toULong(),
	pointer.unusedRangeSizeMax().toULong(),
	pointer.blockCount().toULong()
)
