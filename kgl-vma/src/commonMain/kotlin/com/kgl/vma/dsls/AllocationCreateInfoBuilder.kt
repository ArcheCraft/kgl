package com.kgl.vma.dsls

import com.kgl.vma.enums.*
import com.kgl.vma.handles.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.enums.*
import com.kgl.vulkan.utils.*

@StructMarker
expect class AllocationCreateInfoBuilder {
	var flags: VmaFlag<AllocationCreate>?
	
	var usage: MemoryUsage
	
	var requiredFlags: VkFlag<MemoryProperty>?
	
	var preferredFlags: VkFlag<MemoryProperty>?
	
	var memoryTypeBits: UInt
	
	
	internal fun init(pool: Pool?)
}
