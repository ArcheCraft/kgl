package com.kgl.vma.dsls

import com.kgl.vma.enums.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.handles.*
import com.kgl.vulkan.utils.*
import org.lwjgl.system.*
import org.lwjgl.util.vma.*

actual class AllocatorCreateInfoBuilder(private val target: VmaAllocatorCreateInfo) {
	actual var flags: VmaFlag<AllocatorCreate>?
		get() = AllocatorCreate.fromMultiple(target.flags())
		set(value) { target.flags(value?.value ?: 0) }
	
	
	actual var frameInUseCount: UInt
		get() = target.frameInUseCount().toUInt()
		set(value) { target.frameInUseCount(value.toInt()) }
	
	
	actual var preferredLargeHeapBlockSize: ULong
		get() = target.preferredLargeHeapBlockSize().toULong()
		set(value) { target.preferredLargeHeapBlockSize(value.toLong()) }
	
	actual var heapSizeLimit: ULongArray?
		get() = target.pHeapSizeLimit(memoryHeapCount)?.let { buffer -> ULongArray(memoryHeapCount) { buffer.get(it).toULong() } }
		set(value) {  value?.let { target.pHeapSizeLimit(MemoryStack.stackGet().longs(*it.asLongArray())) } }
	
	actual var vulkanApiVersion: VkVersion
		get() = VkVersion(1u, 0u, 0u)
		set(value) {}
	
	
	actual fun deviceMemoryCallbacks(block: DeviceMemoryCallbacksBuilder.() -> Unit) {
		val target = VmaDeviceMemoryCallbacks.callocStack()
		val builder = DeviceMemoryCallbacksBuilder(target, device!!)
		builder.block()
		this.target.pDeviceMemoryCallbacks(target)
	}
	
	actual fun recordSettings(block: RecordSettingsBuilder.() -> Unit) {
		val target = VmaRecordSettings.callocStack()
		val builder = RecordSettingsBuilder(target)
		builder.block()
		this.target.pRecordSettings(target)
	}
	
	
	private var memoryHeapCount = 0
	
	private var device: Device? = null
	
	internal actual fun init(
		instance: Instance,
		physicalDevice: PhysicalDevice,
		device: Device
	) {
		target.flags(0)
		target.pAllocationCallbacks(null)
		target.physicalDevice(physicalDevice.ptr)
		target.device(device.ptr)
		
		memoryHeapCount = physicalDevice.memoryProperties.memoryHeaps.size
		this.device = device
	}
}
