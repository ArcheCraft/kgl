package com.kgl.vma.handles

import com.kgl.vma.structs.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.utils.*
import cvma.*

actual class Pool(override val ptr: VmaPool, val allocator: Allocator) : VmaHandleNative<VmaPool>(), VmaHandle {
	override fun close() {
		vmaDestroyPool(allocator.ptr, ptr)
	}
	
	
	actual val stats: PoolStats
		get() = getWithVirtualStack(::vmaGetPoolStats, PoolStats.Companion::from, allocator.ptr, ptr)
	
	
	actual fun makeAllocationsLost() {
		vmaMakePoolAllocationsLost(allocator.ptr, ptr, null)
	}
	
	actual fun checkPoolCorruption(): Boolean {
		return when (val result = vmaCheckPoolCorruption(allocator.ptr, ptr)) {
			VK_SUCCESS -> true
			VK_ERROR_VALIDATION_FAILED_EXT -> false
			else -> handleVkResult(result)
		}
	}
}
