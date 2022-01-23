package com.kgl.vma.handles

import com.kgl.vma.dsls.*
import com.kgl.vma.structs.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.enums.*
import com.kgl.vulkan.handles.*
import com.kgl.vulkan.structs.*
import com.kgl.vulkan.utils.*
import org.lwjgl.system.*
import org.lwjgl.util.vma.*
import org.lwjgl.vulkan.*

actual class Allocator(override val ptr: Long, private val device: Device) : VmaHandleJVM<Long>(), VmaHandle {
	override fun close() {
		Vma.vmaDestroyAllocator(ptr)
	}
	
	actual val info: AllocatorInfo
		get() = AllocatorInfo(device.physicalDevice.instance, device.physicalDevice, device)
	
	actual val physicalDeviceProperties: PhysicalDeviceProperties
		get() = getWithMemoryStack(
			Vma::vmaGetPhysicalDeviceProperties,
			{ PhysicalDeviceProperties.from(VkPhysicalDeviceProperties.create(it)) },
			ptr
		)
	
	actual val memoryProperties: PhysicalDeviceMemoryProperties
		get() = getWithMemoryStack(
			Vma::vmaGetMemoryProperties,
			{ PhysicalDeviceMemoryProperties.from(VkPhysicalDeviceMemoryProperties.create(it)) },
			ptr
		)
	
	actual var currentFrameIndex: UInt
		get() = error("")
		set(value) {
			Vma.vmaSetCurrentFrameIndex(ptr, value.toInt())
		}
	
	
	actual fun getMemoryTypeProperties(memoryTypeIndex: UInt): VkFlag<MemoryProperty> {
		return getWithMemoryStack(
			Vma::vmaGetMemoryTypeProperties,
			MemoryProperty.Companion::fromMultiple,
			ptr,
			memoryTypeIndex.toInt()
		)
	}
	
	actual fun calculateStats(): Stats {
		return getWithMemoryStack(VmaStats::callocStack, Vma::vmaCalculateStats, Stats.Companion::from, ptr)
	}
	
	actual fun findMemoryTypeIndex(
		memoryTypes: UInt,
		pool: Pool?,
		allocationCreateInfo: AllocationCreateInfoBuilder.() -> Unit
	): UInt {
		return getWithMemoryStack(
			VmaAllocationCreateInfo::callocStack,
			{ createInfo -> AllocationCreateInfoBuilder(createInfo).also { it.init(pool) } },
			allocationCreateInfo,
			Vma::vmaFindMemoryTypeIndex,
			Int::toUInt,
			ptr,
			memoryTypes.toInt()
		)
	}
	
	actual fun createPool(block: PoolCreateInfoBuilder.() -> Unit): Pool {
		return getWithMemoryStack(
			VmaPoolCreateInfo::callocStack,
			::PoolCreateInfoBuilder,
			block,
			Vma::vmaCreatePool,
			{ Pool(it, this) },
			ptr
		)
	}
	
	actual fun allocateMemoryForBuffer(
		buffer: Buffer,
		pool: Pool?,
		allocationCreateInfo: AllocationCreateInfoBuilder.() -> Unit
	): Allocation {
		MemoryStack.stackPush()
		try {
		    val pointer = VmaAllocationCreateInfo.callocStack()
			val builder = AllocationCreateInfoBuilder(pointer)
			builder.init(pool)
			builder.allocationCreateInfo()
			val result = MemoryStack.stackCallocPointer(1)
			Vma.vmaAllocateMemoryForBuffer(ptr, buffer.ptr, pointer, result, null)
			return Allocation(result[0], this, device)
		} finally {
		    MemoryStack.stackPop()
		}
	}
	
	actual fun allocateMemoryForImage(
		image: Image,
		pool: Pool?,
		allocationCreateInfo: AllocationCreateInfoBuilder.() -> Unit
	): Allocation {
		MemoryStack.stackPush()
		try {
		    val pointer = VmaAllocationCreateInfo.callocStack()
			val builder = AllocationCreateInfoBuilder(pointer)
			builder.init(pool)
			builder.allocationCreateInfo()
			val result = MemoryStack.stackCallocPointer(1)
			Vma.vmaAllocateMemoryForImage(ptr, image.ptr, pointer, result, null)
			return Allocation(result[0], this, device)
		} finally {
		    MemoryStack.stackPop()
		}
	}
	
	actual fun freeMemoryPages(allocations: List<Allocation>) {
		MemoryStack.stackPush()
		try {
		    val pointers = MemoryStack.stackPointers(*LongArray(allocations.size) { allocations[it].ptr })
			Vma.vmaFreeMemoryPages(ptr, pointers)
		} finally {
		    MemoryStack.stackPop()
		}
	}
	
	actual fun createLostAllocation(): Allocation {
		MemoryStack.stackPush()
		try {
			val result = MemoryStack.stackCallocPointer(1)
			Vma.vmaCreateLostAllocation(ptr, result)
			return Allocation(result[0], this, device)
		} finally {
			MemoryStack.stackPop()
		}
	}
	
	actual fun defragmentationBegin(
		allocations: Set<Allocation>,
		pools: Set<Pool>?,
		commandBuffer: CommandBuffer?,
		builder: DefragmentationBeginInfoBuilder.() -> Unit
	): DefragmentationContext? {
		MemoryStack.stackPush()
		try {
			val pointer = VmaDefragmentationInfo2.callocStack()
			val build = DefragmentationBeginInfoBuilder(pointer)
			build.init(allocations, pools, commandBuffer)
			build.builder()
			val result = MemoryStack.stackCallocPointer(1)
			return when (val code = Vma.vmaDefragmentationBegin(ptr, pointer, null, result)) {
				VK10.VK_SUCCESS -> null
				VK10.VK_NOT_READY -> DefragmentationContext(this, result[0])
				else -> handleVkResult(code)
			}
			
		} finally {
			MemoryStack.stackPop()
		}
	}
	
	actual fun defragmentationEnd(context: DefragmentationContext?) {
		if (context != null) {
			Vma.vmaDefragmentationEnd(ptr, context.handle)
		}
	}
}


actual fun Device.createAllocator(block: AllocatorCreateInfoBuilder.() -> Unit): Allocator {
	return getWithMemoryStack(
		VmaAllocatorCreateInfo::callocStack,
		{ createInfo ->
			AllocatorCreateInfoBuilder(createInfo).also {
				it.init(
					physicalDevice.instance,
					physicalDevice,
					this
				)
			}
		},
		block,
		Vma::vmaCreateAllocator,
		{ Allocator(it, this) })
}
