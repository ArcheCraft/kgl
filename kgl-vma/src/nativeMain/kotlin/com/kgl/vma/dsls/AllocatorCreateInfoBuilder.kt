package com.kgl.vma.dsls

import com.kgl.core.*
import com.kgl.vma.enums.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.handles.*
import com.kgl.vulkan.utils.*
import cvma.*
import kotlinx.cinterop.*

actual class AllocatorCreateInfoBuilder(private val target: VmaAllocatorCreateInfo) {
	actual var flags: VmaFlag<AllocatorCreate>?
		get() = AllocatorCreate.fromMultiple(target.flags)
		set(value) { target.flags = value?.value ?: 0u }
	
	actual var frameInUseCount: UInt
		get() = target.frameInUseCount
		set(value) { target.frameInUseCount = value }
	
	actual var preferredLargeHeapBlockSize: ULong
		get() = target.preferredLargeHeapBlockSize
		set(value) { target.preferredLargeHeapBlockSize = value }
	
	actual var heapSizeLimit: ULongArray?
		get() = target.pHeapSizeLimit?.let { array -> ULongArray(memoryHeapCount) { array[it] } }
		set(value) { target.pHeapSizeLimit = value?.refTo(0)?.getPointer(VirtualStack.currentFrame!!) }
	
	actual var vulkanApiVersion: VkVersion
		get() = VkVersion(target.vulkanApiVersion)
		set(value) { target.vulkanApiVersion = value.value }
	
	
	actual fun deviceMemoryCallbacks(block: DeviceMemoryCallbacksBuilder.() -> Unit) {
		val target = VirtualStack.alloc<VmaDeviceMemoryCallbacks>().ptr
		val builder = DeviceMemoryCallbacksBuilder(target.pointed, device!!)
		builder.block()
		this.target.pDeviceMemoryCallbacks = target
	}
	
	actual fun recordSettings(block: RecordSettingsBuilder.() -> Unit) {
		val target = VirtualStack.alloc<VmaRecordSettings>().ptr
		val builder = RecordSettingsBuilder(target.pointed)
		builder.block()
		this.target.pRecordSettings = target
	}
	
	
	private var memoryHeapCount = 0
	
	private var device: Device? = null
	
	internal actual fun init(
		instance: Instance,
		physicalDevice: PhysicalDevice,
		device: Device
	) {
		target.flags = 0u
		target.pAllocationCallbacks = null
		target.instance = instance.ptr
		target.physicalDevice = physicalDevice.ptr
		target.device = device.ptr
		
		memoryHeapCount = physicalDevice.memoryProperties.memoryHeaps.size
		this.device = device
	}
}
