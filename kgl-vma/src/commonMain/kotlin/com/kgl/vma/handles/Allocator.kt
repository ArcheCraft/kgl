package com.kgl.vma.handles

import com.kgl.vma.dsls.*
import com.kgl.vma.structs.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.dsls.*
import com.kgl.vulkan.enums.*
import com.kgl.vulkan.handles.*
import com.kgl.vulkan.structs.*
import com.kgl.vulkan.utils.*


expect class Allocator : VmaHandle {
	val info: AllocatorInfo
	
	val physicalDeviceProperties: PhysicalDeviceProperties
	
	val memoryProperties: PhysicalDeviceMemoryProperties
	
	@get:Deprecated("currentFrameIndex can't be queried!", level = DeprecationLevel.ERROR)
	var currentFrameIndex: UInt
	
	
	fun getMemoryTypeProperties(memoryTypeIndex: UInt): VkFlag<MemoryProperty>
	
	fun calculateStats(): Stats
	
	fun findMemoryTypeIndex(memoryTypes: UInt, pool: Pool? = null, allocationCreateInfo: AllocationCreateInfoBuilder.() -> Unit): UInt
	
	fun createPool(block: PoolCreateInfoBuilder.() -> Unit): Pool
	
	fun allocateMemoryForBuffer(buffer: Buffer, pool: Pool? = null, allocationCreateInfo: AllocationCreateInfoBuilder.() -> Unit): Allocation
	
	fun allocateMemoryForImage(image: Image, pool: Pool? = null, allocationCreateInfo: AllocationCreateInfoBuilder.() -> Unit): Allocation
	
	fun freeMemoryPages(allocations: List<Allocation>)
	
	fun createLostAllocation(): Allocation
	
	fun defragmentationBegin(allocations: Set<Allocation>, pools: Set<Pool>?, commandBuffer: CommandBuffer?, builder: DefragmentationBeginInfoBuilder.() -> Unit): DefragmentationContext?
	
	fun defragmentationEnd(context: DefragmentationContext?)
}


expect fun Device.createAllocator(block: AllocatorCreateInfoBuilder.() -> Unit): Allocator
