package com.kgl.vma.dsls

import com.kgl.vulkan.handles.*
import org.lwjgl.util.vma.*

actual class DeviceMemoryCallbacksBuilder(private val target: VmaDeviceMemoryCallbacks, private val device: Device) {
	actual fun allocate(callback: AllocateDeviceMemoryFunction) {
		target.pfnAllocate { _, memoryType, memory, size ->
			callback(DeviceMemory(memory, device, size.toULong(), memoryType.toUInt()))
		}
	}
	
	actual fun free(callback: FreeDeviceMemoryFunction) {
		target.pfnFree { _, memoryType, memory, size ->
			callback(DeviceMemory(memory, device, size.toULong(), memoryType.toUInt()))
		}
	}
}
