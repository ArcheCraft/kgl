package com.kgl.vma.handles

import com.kgl.vma.structs.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.utils.*
import org.lwjgl.util.vma.*
import org.lwjgl.vulkan.*

actual class Pool(override val ptr: Long, val allocator: Allocator) : VmaHandleJVM<Long>(), VmaHandle {
	override fun close() {
		Vma.vmaDestroyPool(allocator.ptr, ptr)
	}
	
	
	actual val stats: PoolStats
		get() = getWithMemoryStack(VmaPoolStats::callocStack, Vma::vmaGetPoolStats, PoolStats.Companion::from, allocator.ptr, ptr)
	
	
	actual fun makeAllocationsLost() {
		Vma.vmaMakePoolAllocationsLost(allocator.ptr, ptr)
	}
	
	actual fun checkPoolCorruption(): Boolean {
		return when (val result = Vma.vmaCheckPoolCorruption(allocator.ptr, ptr)) {
			VK10.VK_SUCCESS -> true
			EXTDebugReport.VK_ERROR_VALIDATION_FAILED_EXT -> false
			else -> handleVkResult(result)
		}
	}
}
