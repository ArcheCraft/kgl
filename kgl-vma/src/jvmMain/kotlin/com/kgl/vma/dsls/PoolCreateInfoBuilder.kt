package com.kgl.vma.dsls

import com.kgl.vma.enums.*
import com.kgl.vma.utils.*
import org.lwjgl.util.vma.*

actual class PoolCreateInfoBuilder(private val target: VmaPoolCreateInfo) {
	actual var memoryTypeIndex: UInt
		get() = target.memoryTypeIndex().toUInt()
		set(value) { target.memoryTypeIndex(value.toInt()) }
	
	actual var flags: VmaFlag<PoolCreate>?
		get() = PoolCreate.fromMultiple(target.flags())
	    set(value) { target.flags(value?.value ?: 0) }
	
	actual var blockSize: ULong
		get() = target.blockSize().toULong()
		set(value) { target.blockSize(value.toLong()) }
	
	actual var minBlockCount: ULong
		get() = target.minBlockCount().toULong()
		set(value) { target.blockSize(value.toLong()) }
	
	actual var maxBlockCount: ULong
		get() = target.maxBlockCount().toULong()
		set(value) { target.maxBlockCount(value.toLong()) }
	
	actual var frameInUseCount: UInt
		get() = target.frameInUseCount().toUInt()
		set(value) { target.frameInUseCount(value.toInt()) }
}
