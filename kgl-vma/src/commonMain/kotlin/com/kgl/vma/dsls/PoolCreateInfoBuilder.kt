package com.kgl.vma.dsls

import com.kgl.vma.enums.*
import com.kgl.vma.utils.*

expect class PoolCreateInfoBuilder {
	var memoryTypeIndex: UInt
	
	var flags: VmaFlag<PoolCreate>?
	
	var blockSize: ULong
	
	var minBlockCount: ULong
	
	var maxBlockCount: ULong
	
	var frameInUseCount: UInt
}
