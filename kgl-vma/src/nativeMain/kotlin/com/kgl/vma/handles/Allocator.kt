package com.kgl.vma.handles

import com.kgl.core.*
import com.kgl.vma.dsls.*
import com.kgl.vma.structs.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.dsls.*
import com.kgl.vulkan.enums.*
import com.kgl.vulkan.handles.*
import com.kgl.vulkan.structs.*
import com.kgl.vulkan.utils.*
import cvma.*
import kotlinx.cinterop.*

actual class Allocator(override val ptr: VmaAllocator, internal val device: Device) : VmaHandleNative<VmaAllocator>(), VmaHandle {
	override fun close() {
		vmaDestroyAllocator(ptr)
	}
	
	actual val info: AllocatorInfo
		get() = AllocatorInfo(device.physicalDevice.instance, device.physicalDevice, device)
	
	actual val physicalDeviceProperties: PhysicalDeviceProperties
		get() = getWithVirtualStack(::vmaGetPhysicalDeviceProperties, { PhysicalDeviceProperties.from(it.reinterpret()) }, ptr)
	
	actual val memoryProperties: PhysicalDeviceMemoryProperties
		get() = getWithVirtualStack(::vmaGetMemoryProperties, { PhysicalDeviceMemoryProperties.from(it.reinterpret()) }, ptr)
	
	actual var currentFrameIndex: UInt
		get() = error("")
		set(value) { vmaSetCurrentFrameIndex(ptr, value) }
	
	
	actual fun getMemoryTypeProperties(memoryTypeIndex: UInt): VkFlag<MemoryProperty> {
		return getWithVirtualStack(::vmaGetMemoryTypeProperties, MemoryProperty.Companion::fromMultiple, ptr, memoryTypeIndex)
	}
	
	actual fun calculateStats(): Stats {
		return getWithVirtualStack(::vmaCalculateStats, Stats.Companion::from, ptr)
	}
	
	actual fun findMemoryTypeIndex(
		memoryTypes: UInt,
		pool: Pool?,
		allocationCreateInfo: AllocationCreateInfoBuilder.() -> Unit
	): UInt {
		return getWithVirtualStack({ createInfo -> AllocationCreateInfoBuilder(createInfo).also { it.init(pool) } }, allocationCreateInfo, ::vmaFindMemoryTypeIndex, ptr, memoryTypes)
	}
	
	actual fun createPool(block: PoolCreateInfoBuilder.() -> Unit): Pool {
		return getWithVirtualStack(::PoolCreateInfoBuilder, block, ::vmaCreatePool, { Pool(it, this) }, ptr)
	}
	
	actual fun allocateMemoryForBuffer(
		buffer: Buffer,
		pool: Pool?,
		allocationCreateInfo: AllocationCreateInfoBuilder.() -> Unit
	): Allocation {
		VirtualStack.push()
		try {
			val pointer = VirtualStack.alloc<VmaAllocationCreateInfo>()
			val builder = AllocationCreateInfoBuilder(pointer)
			builder.init(pool)
			builder.allocationCreateInfo()
			val result = VirtualStack.alloc<VmaAllocationVar>()
			vmaAllocateMemoryForBuffer(ptr, buffer.ptr, pointer.ptr, result.ptr, null)
			return Allocation(result.value!!, this, device)
		} finally {
			VirtualStack.pop()
		}
	}
	
	actual fun allocateMemoryForImage(
		image: Image,
		pool: Pool?,
		allocationCreateInfo: AllocationCreateInfoBuilder.() -> Unit
	): Allocation {
		VirtualStack.push()
		try {
			val pointer = VirtualStack.alloc<VmaAllocationCreateInfo>()
			val builder = AllocationCreateInfoBuilder(pointer)
			builder.init(pool)
			builder.allocationCreateInfo()
			val result = VirtualStack.alloc<VmaAllocationVar>()
			vmaAllocateMemoryForImage(ptr, image.ptr, pointer.ptr, result.ptr, null)
			return Allocation(result.value!!, this, device)
		} finally {
			VirtualStack.pop()
		}
	}
	
	actual fun freeMemoryPages(allocations: List<Allocation>) {
		VirtualStack.push()
		try {
			val pointers = VirtualStack.allocArrayOf(allocations.map { it.ptr })
			vmaFreeMemoryPages(ptr, allocations.size.toUInt().toULong(), pointers)
		} finally {
		    VirtualStack.pop()
		}
	}
	
	actual fun createLostAllocation(): Allocation {
		VirtualStack.push()
		try {
			val result = VirtualStack.alloc<VmaAllocationVar>()
			vmaCreateLostAllocation(ptr, result.ptr)
			return Allocation(result.value!!, this, device)
		} finally {
			VirtualStack.pop()
		}
	}
	
	actual fun defragmentationBegin(
		allocations: Set<Allocation>,
		pools: Set<Pool>?,
		commandBuffer: CommandBuffer?,
		builder: DefragmentationBeginInfoBuilder.() -> Unit
	): DefragmentationContext? {
		VirtualStack.push()
		try {
			val pointer = VirtualStack.alloc<VmaDefragmentationInfo2>()
			val build = DefragmentationBeginInfoBuilder(pointer)
			build.init(allocations, pools, commandBuffer)
			build.builder()
			val result = VirtualStack.alloc<VmaDefragmentationContextVar>()
			return when (val code = vmaDefragmentationBegin(ptr, pointer.ptr, null, result.ptr)) {
				VK_SUCCESS -> null
				VK_NOT_READY -> DefragmentationContext(this, result.value!!)
				else -> handleVkResult(code)
			}
		} finally {
			VirtualStack.pop()
		}
	}
	
	actual fun defragmentationEnd(context: DefragmentationContext?) {
		if (context != null) {
			vmaDefragmentationEnd(ptr, context.handle)
		}
	}
}


actual fun Device.createAllocator(block: AllocatorCreateInfoBuilder.() -> Unit): Allocator {
	return getWithVirtualStack({ createInfo -> AllocatorCreateInfoBuilder(createInfo).also { it.init(physicalDevice.instance, physicalDevice, this) } }, block, ::vmaCreateAllocator, { Allocator(it, this) })
}




fun Allocator.allocateMemory(
	memoryRequirements: MemoryRequirements,
	pool: Pool?,
	allocationCreateInfo: AllocationCreateInfoBuilder.() -> Unit
): Allocation {
	VirtualStack.push()
	try {
		val requirements = VirtualStack.alloc<VkMemoryRequirements>()
		requirements.alignment = memoryRequirements.alignment
		requirements.size = memoryRequirements.size
		requirements.memoryTypeBits = memoryRequirements.memoryTypeBits
		
		val pointer = VirtualStack.alloc<VmaAllocationCreateInfo>()
		val builder = AllocationCreateInfoBuilder(pointer)
		builder.init(pool)
		builder.allocationCreateInfo()
		val result = VirtualStack.alloc<VmaAllocationVar>()
		vmaAllocateMemory(ptr, requirements.ptr, pointer.ptr, result.ptr, null)
		return Allocation(result.value!!, this, device)
	} finally {
		VirtualStack.pop()
	}
}

fun Allocator.allocateMemoryPages(
	memoryRequirements: MemoryRequirements,
	allocationCount: ULong,
	pool: Pool?,
	allocationCreateInfo: AllocationCreateInfoBuilder.() -> Unit
): List<Allocation> {
	VirtualStack.push()
	try {
		val requirements = VirtualStack.alloc<VkMemoryRequirements>()
		requirements.alignment = memoryRequirements.alignment
		requirements.size = memoryRequirements.size
		requirements.memoryTypeBits = memoryRequirements.memoryTypeBits
		
		val pointer = VirtualStack.alloc<VmaAllocationCreateInfo>()
		val builder = AllocationCreateInfoBuilder(pointer)
		builder.init(pool)
		builder.allocationCreateInfo()
		val result = VirtualStack.allocArray<VmaAllocationVar>(allocationCount.toInt())
		vmaAllocateMemoryPages(ptr, requirements.ptr, pointer.ptr, allocationCount, result, null)
		return List(allocationCount.toInt()) { Allocation(result[it]!!, this, device) }
	} finally {
		VirtualStack.pop()
	}
}
