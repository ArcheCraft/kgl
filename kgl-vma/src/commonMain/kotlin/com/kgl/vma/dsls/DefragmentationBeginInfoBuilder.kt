package com.kgl.vma.dsls

import com.kgl.vma.handles.*
import com.kgl.vulkan.handles.*

expect class DefragmentationBeginInfoBuilder {
	var maxCpuBytesToMove: ULong
	
	var maxCpuAllocationsToMove: UInt
	
	var maxGpuBytesToMove: ULong
	
	var maxGpuAllocationsToMove: UInt
	
	
	internal fun init(allocations: Set<Allocation>, pools: Set<Pool>?, commandBuffer: CommandBuffer?)
}
