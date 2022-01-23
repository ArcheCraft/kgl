package com.kgl.vma.dsls

import com.kgl.vma.enums.*
import com.kgl.vma.handles.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.enums.*
import com.kgl.vulkan.utils.*
import org.lwjgl.util.vma.*

actual class AllocationCreateInfoBuilder(private val target: VmaAllocationCreateInfo) {
	actual var flags: VmaFlag<AllocationCreate>?
		get() = AllocationCreate.fromMultiple(target.flags())
		set(value) { target.flags(value?.value ?: 0) }
	
	actual var usage: MemoryUsage
		get() = MemoryUsage.from(target.usage())
		set(value) { target.usage(value.value) }
	
	actual var requiredFlags: VkFlag<MemoryProperty>?
		get() = MemoryProperty.fromMultiple(target.requiredFlags())
		set(value) { target.requiredFlags(value?.value ?: 0) }
	
	actual var preferredFlags: VkFlag<MemoryProperty>?
		get() = MemoryProperty.fromMultiple(target.preferredFlags())
		set(value) { target.preferredFlags(value?.value ?: 0) }
	
	actual var memoryTypeBits: UInt
		get() = target.memoryTypeBits().toUInt()
		set(value) { target.memoryTypeBits(value.toInt()) }
	
	
	internal actual fun init(pool: Pool?) {
		target.pool(pool?.ptr ?: 0)
	}
}
