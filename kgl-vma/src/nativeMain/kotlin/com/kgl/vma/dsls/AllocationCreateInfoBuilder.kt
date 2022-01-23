package com.kgl.vma.dsls

import com.kgl.vma.enums.*
import com.kgl.vma.handles.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.enums.*
import com.kgl.vulkan.utils.*
import cvma.*

actual class AllocationCreateInfoBuilder(private val target: VmaAllocationCreateInfo) {
	actual var flags: VmaFlag<AllocationCreate>?
		get() = AllocationCreate.fromMultiple(target.flags)
		set(value) { target.flags = value?.value ?: 0u }
	
	actual var usage: MemoryUsage
		get() = MemoryUsage.from(target.usage)
		set(value) { target.usage = value.value }
	
	actual var requiredFlags: VkFlag<MemoryProperty>?
		get() = MemoryProperty.fromMultiple(target.requiredFlags)
		set(value) { target.requiredFlags = value?.value ?: 0u }
	
	actual var preferredFlags: VkFlag<MemoryProperty>?
		get() = MemoryProperty.fromMultiple(target.preferredFlags)
		set(value) { target.preferredFlags = value?.value ?: 0u }
	
	actual var memoryTypeBits: UInt
		get() = target.memoryTypeBits
		set(value) { target.memoryTypeBits = value }
	
	
	internal actual fun init(pool: Pool?) {
		target.pool = pool?.ptr
	}
}
