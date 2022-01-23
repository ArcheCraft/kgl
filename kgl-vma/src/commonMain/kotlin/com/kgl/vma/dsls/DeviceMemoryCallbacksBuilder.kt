package com.kgl.vma.dsls

import com.kgl.vulkan.handles.*

typealias AllocateDeviceMemoryFunction = (memory: DeviceMemory) -> Unit

typealias FreeDeviceMemoryFunction = (memory: DeviceMemory) -> Unit

expect class DeviceMemoryCallbacksBuilder {
	fun allocate(callback: AllocateDeviceMemoryFunction)
	
	fun free(callback: FreeDeviceMemoryFunction)
}
