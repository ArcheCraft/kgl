package com.kgl.vma.enums

import com.kgl.vma.utils.*
import cvma.*

actual enum class MemoryUsage(override val value: UInt) : VmaEnum<MemoryUsage> {
	UNKNOWN(VMA_MEMORY_USAGE_UNKNOWN),
	GPU_ONLY(VMA_MEMORY_USAGE_GPU_ONLY),
	CPU_ONLY(VMA_MEMORY_USAGE_CPU_ONLY),
	CPU_TO_GPU(VMA_MEMORY_USAGE_CPU_TO_GPU),
	GPU_TO_CPU(VMA_MEMORY_USAGE_GPU_TO_CPU);
	
	companion object {
		private val enumLookUpMap: Map<UInt, MemoryUsage> =
			enumValues<MemoryUsage>().associateBy { it.value }
		
		fun from(value: UInt): MemoryUsage = enumLookUpMap[value]!!
	}
}
