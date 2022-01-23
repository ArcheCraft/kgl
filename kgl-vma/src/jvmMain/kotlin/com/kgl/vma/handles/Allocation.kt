package com.kgl.vma.handles

import com.kgl.vma.structs.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.handles.*
import com.kgl.vulkan.utils.*
import io.ktor.utils.io.bits.*
import org.lwjgl.system.*
import org.lwjgl.util.vma.*
import org.lwjgl.vulkan.*

actual class Allocation(override val ptr: Long, val allocator: Allocator, val device: Device) : VmaHandleJVM<Long>(), VmaHandle {
	override fun close() {
		Vma.vmaFreeMemory(allocator.ptr, ptr)
	}
	
	
	actual val info: AllocationInfo
		get() = getWithMemoryStack(VmaAllocationInfo::callocStack, Vma::vmaGetAllocationInfo, this::createAllocationInfo, allocator.ptr, ptr)
	
	
	actual fun touch() {
		Vma.vmaTouchAllocation(allocator.ptr, ptr)
	}
	
	actual fun map(): Memory {
		MemoryStack.stackPush()
		try {
		    val outputVar = MemoryStack.stackCallocPointer(1)
			val result = Vma.vmaMapMemory(allocator.ptr, ptr, outputVar)
			if (result != VK10.VK_SUCCESS) handleVkResult(result)
			return Memory.of(outputVar.getByteBuffer(info.size.toInt()))
		} finally {
		    MemoryStack.stackPop()
		}
	}
	
	actual fun unmap() {
		Vma.vmaUnmapMemory(allocator.ptr, ptr)
	}
	
	actual fun flush(offset: ULong, size: ULong) {
		Vma.vmaFlushAllocation(allocator.ptr, ptr, offset.toLong(), size.toLong())
	}
	
	actual fun invalidate(offset: ULong, size: ULong) {
		Vma.vmaInvalidateAllocation(allocator.ptr, ptr, offset.toLong(), size.toLong())
	}
	
	actual fun bindBufferMemory(buffer: Buffer) {
		Vma.vmaBindBufferMemory(allocator.ptr, ptr, buffer.ptr)
	}
	
	actual fun bindImageMemory(image: Image) {
		Vma.vmaBindImageMemory(allocator.ptr, ptr, image.ptr)
	}
	
	
	private fun createAllocationInfo(pointer: VmaAllocationInfo) = AllocationInfo.from(pointer, device)
}
