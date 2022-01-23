package com.kgl.vma.structs

import org.lwjgl.util.vma.*

fun StatInfo.Companion.from(pointer: VmaStatInfo) = StatInfo(
	pointer.blockCount().toUInt(),
	pointer.allocationCount().toUInt(),
	pointer.unusedRangeCount().toUInt(),
	pointer.usedBytes().toULong(),
	pointer.unusedBytes().toULong(),
	pointer.allocationSizeMin().toULong(),
	pointer.allocationSizeAvg().toULong(),
	pointer.allocationSizeMax().toULong(),
	pointer.unusedRangeSizeMin().toULong(),
	pointer.unusedRangeSizeAvg().toULong(),
	pointer.unusedRangeSizeMax().toULong()
)
