package com.kgl.vma.structs

import com.kgl.vulkan.handles.*
import cvma.*
import kotlinx.cinterop.*

fun AllocationInfo.Companion.from(pointer: VmaAllocationInfo, device: Device) = AllocationInfo(
	pointer.memoryType,
	DeviceMemory(pointer.deviceMemory!!.reinterpret(), device, pointer.size, pointer.memoryType),
	pointer.offset,
	pointer.size
)
