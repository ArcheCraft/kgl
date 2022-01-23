package com.kgl.vma.structs

data class StatInfo(
	val blockCount: UInt,
	val allocationCount: UInt,
	val unusedRangeCount: UInt,
	val usedBytes: ULong,
	val unusedBytes: ULong,
	val allocationSizeMin: ULong,
	val allocationSizeAvg: ULong,
	val allocationSizeMax: ULong,
	val unusedRangeSizeMin: ULong,
	val unusedRangeSizeAvg: ULong,
	val unusedRangeSizeMax: ULong
) {
	companion object
}
