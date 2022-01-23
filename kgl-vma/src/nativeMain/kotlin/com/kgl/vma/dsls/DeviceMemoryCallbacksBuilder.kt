package com.kgl.vma.dsls

import com.kgl.vulkan.handles.*
import cvma.*
import kotlinx.cinterop.*


actual class DeviceMemoryCallbacksBuilder(private val target: VmaDeviceMemoryCallbacks, private val device: Device) {
	actual fun allocate(callback: AllocateDeviceMemoryFunction) {
		target.pfnAllocate = staticCFunction { _, memoryType, memory, size ->
			callback(DeviceMemory(memory!!.reinterpret(), device, size, memoryType))
		}
	}
	
	actual fun free(callback: FreeDeviceMemoryFunction) {
		target.pfnFree = staticCFunction { _, memoryType, memory, size ->
			callback(DeviceMemory(memory!!.reinterpret(), device, size, memoryType))
		}
	}
}
