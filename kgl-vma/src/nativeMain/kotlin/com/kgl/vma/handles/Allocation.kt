package com.kgl.vma.handles

import com.kgl.core.*
import com.kgl.vma.structs.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.handles.*
import com.kgl.vulkan.utils.*
import cvma.*
import io.ktor.utils.io.bits.*
import kotlinx.cinterop.*

actual class Allocation(override val ptr: VmaAllocation, val allocator: Allocator, val device: Device) : VmaHandleNative<VmaAllocation>(), VmaHandle {
	override fun close() {
		vmaFreeMemory(allocator.ptr, ptr)
	}
	
	
	actual val info: AllocationInfo
		get() = getWithVirtualStack(::vmaGetAllocationInfo, this::createAllocationInfo, allocator.ptr, ptr)
	
	
	actual fun touch() {
		vmaTouchAllocation(allocator.ptr, ptr)
	}
	
	actual fun map(): Memory {
		VirtualStack.push()
		try {
			val outputVar = VirtualStack.alloc<COpaquePointerVar>()
			val result = vmaMapMemory(allocator.ptr, ptr, outputVar.ptr)
			if (result != cvulkan.VK_SUCCESS) handleVkResult(result)
			return Memory.of(outputVar.value!!, info.size.toInt())
		} finally {
			VirtualStack.pop()
		}
	}
	
	actual fun unmap() {
		vmaUnmapMemory(allocator.ptr, ptr)
	}
	
	actual fun flush(offset: ULong, size: ULong) {
		vmaFlushAllocation(allocator.ptr, ptr, offset, size)
	}
	
	actual fun invalidate(offset: ULong, size: ULong) {
		vmaInvalidateAllocation(allocator.ptr, ptr, offset, size)
	}
	
	actual fun bindBufferMemory(buffer: Buffer) {
		vmaBindBufferMemory(allocator.ptr, ptr, buffer.ptr)
	}
	
	actual fun bindImageMemory(image: Image) {
		vmaBindImageMemory(allocator.ptr, ptr, image.ptr)
	}
	
	
	private fun createAllocationInfo(pointer: VmaAllocationInfo) = AllocationInfo.from(pointer, device)
}
