package com.kgl.vma.dsls

import com.kgl.vma.handles.Allocation
import com.kgl.vma.handles.Pool
import com.kgl.vulkan.handles.CommandBuffer
import org.lwjgl.system.*
import org.lwjgl.util.vma.*

actual class DefragmentationBeginInfoBuilder(private val target: VmaDefragmentationInfo2) {
	actual var maxCpuBytesToMove: ULong
		get() = target.maxCpuBytesToMove().toULong()
		set(value) { target.maxCpuBytesToMove(value.toLong()) }
	actual var maxCpuAllocationsToMove: UInt
		get() = target.maxCpuAllocationsToMove().toUInt()
		set(value) { target.maxCpuAllocationsToMove(value.toInt()) }
	actual var maxGpuBytesToMove: ULong
		get() = target.maxGpuBytesToMove().toULong()
		set(value) { target.maxGpuBytesToMove(value.toLong()) }
	actual var maxGpuAllocationsToMove: UInt
		get() = target.maxGpuAllocationsToMove().toUInt()
		set(value) { target.maxGpuAllocationsToMove(value.toInt()) }
	
	internal actual fun init(allocations: Set<Allocation>, pools: Set<Pool>?, commandBuffer: CommandBuffer?) {
		target.flags(0)
		target.allocationCount(allocations.size)
		target.pAllocations(MemoryStack.stackPointers(*allocations.map(Allocation::ptr).toLongArray()))
		if (pools != null) {
			target.poolCount(pools.size)
			target.pPools(MemoryStack.stackPointers(*pools.map(Pool::ptr).toLongArray()))
		}
		target.commandBuffer(commandBuffer?.ptr)
	}
}
