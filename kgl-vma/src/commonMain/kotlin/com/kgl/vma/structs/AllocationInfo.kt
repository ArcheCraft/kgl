package com.kgl.vma.structs

import com.kgl.vulkan.handles.*

data class AllocationInfo(val memoryType: UInt, val deviceMemory: DeviceMemory, val offset: ULong, val size: ULong) {
	companion object
}
