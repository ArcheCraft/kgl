package com.kgl.vma.dsls

import com.kgl.vma.enums.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.handles.*
import com.kgl.vulkan.utils.*

@StructMarker
expect class AllocatorCreateInfoBuilder {
	var flags: VmaFlag<AllocatorCreate>?
	
	var preferredLargeHeapBlockSize: ULong
	
	var frameInUseCount: UInt
	
	var heapSizeLimit: ULongArray?
	
	var vulkanApiVersion: VkVersion
	
	
	fun deviceMemoryCallbacks(block: DeviceMemoryCallbacksBuilder.() -> Unit)
	
	fun recordSettings(block: RecordSettingsBuilder.() -> Unit)
	
	
	internal fun init(instance: Instance, physicalDevice: PhysicalDevice, device: Device)
}
