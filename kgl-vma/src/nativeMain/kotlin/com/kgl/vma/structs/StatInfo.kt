package com.kgl.vma.structs

import cvma.*

fun StatInfo.Companion.from(pointer: VmaStatInfo) = StatInfo(
	pointer.blockCount,
	pointer.allocationCount,
	pointer.unusedRangeCount,
	pointer.usedBytes,
	pointer.unusedBytes,
	pointer.allocationSizeMin,
	pointer.allocationSizeAvg,
	pointer.allocationSizeMax,
	pointer.unusedRangeSizeMin,
	pointer.unusedRangeSizeAvg,
	pointer.unusedRangeSizeMax
)
