package com.kgl.vma.dsls

import com.kgl.vma.enums.*
import com.kgl.vma.utils.*
import cvma.*

actual class PoolCreateInfoBuilder(private val target: VmaPoolCreateInfo) {
	actual var memoryTypeIndex: UInt
		get() = target.memoryTypeIndex
		set(value) { target.memoryTypeIndex = value }
	
	actual var flags: VmaFlag<PoolCreate>?
		get() = PoolCreate.fromMultiple(target.flags)
		set(value) { target.flags = value?.value ?: 0u }
	
	actual var blockSize: ULong
		get() = target.blockSize
		set(value) { target.blockSize = value }
	
	actual var minBlockCount: ULong
		get() = target.minBlockCount
		set(value) { target.blockSize = value }
	
	actual var maxBlockCount: ULong
		get() = target.maxBlockCount
		set(value) { target.maxBlockCount = value }
	
	actual var frameInUseCount: UInt
		get() = target.frameInUseCount
		set(value) { target.frameInUseCount = value }
}
