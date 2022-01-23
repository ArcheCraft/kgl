package com.kgl.vma.structs

import com.kgl.vulkan.handles.*
import org.lwjgl.util.vma.*

fun AllocationInfo.Companion.from(pointer: VmaAllocationInfo, device: Device) = AllocationInfo(
	pointer.memoryType().toUInt(),
	DeviceMemory(pointer.deviceMemory(), device, pointer.size().toULong(), pointer.memoryType().toUInt()),
	pointer.offset().toULong(),
	pointer.size().toULong()
)
