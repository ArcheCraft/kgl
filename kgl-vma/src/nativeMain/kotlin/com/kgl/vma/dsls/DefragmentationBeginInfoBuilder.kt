package com.kgl.vma.dsls

import com.kgl.core.*
import com.kgl.vma.handles.Allocation
import com.kgl.vma.handles.Pool
import com.kgl.vulkan.handles.CommandBuffer
import cvma.*
import kotlinx.cinterop.*

actual class DefragmentationBeginInfoBuilder(private val target: VmaDefragmentationInfo2) {
    actual var maxCpuBytesToMove: ULong
        get() = target.maxCpuBytesToMove
        set(value) { target.maxCpuBytesToMove = value }
    actual var maxCpuAllocationsToMove: UInt
        get() = target.maxCpuAllocationsToMove
        set(value) { target.maxCpuAllocationsToMove = value }
    actual var maxGpuBytesToMove: ULong
        get() = target.maxGpuBytesToMove
        set(value) { target.maxGpuBytesToMove = value }
    actual var maxGpuAllocationsToMove: UInt
        get() = target.maxGpuAllocationsToMove
        set(value) { target.maxGpuAllocationsToMove = value }
    
    internal actual fun init(allocations: Set<Allocation>, pools: Set<Pool>?, commandBuffer: CommandBuffer?) {
		target.flags = 0u
	    target.allocationCount = allocations.size.toUInt()
	    target.pAllocations = VirtualStack.allocArrayOf(allocations.map(Allocation::ptr))
	    if (pools != null) {
			target.poolCount = pools.size.toUInt()
		    target.pPools = VirtualStack.allocArrayOf(pools.map(Pool::ptr))
	    }
	    target.commandBuffer = commandBuffer?.ptr
    }
}
