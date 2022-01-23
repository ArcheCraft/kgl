package com.kgl.vma.structs

data class PoolStats(
	val size: ULong,
	val unusedSize: ULong,
	val allocationCount: ULong,
	val unusedRangeCount: ULong,
	val unusedRangeSizeMax: ULong,
	val blockCount: ULong
) {
	companion object
}
