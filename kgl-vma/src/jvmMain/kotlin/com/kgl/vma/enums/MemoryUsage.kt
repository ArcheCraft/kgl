package com.kgl.vma.enums

import com.kgl.vma.utils.*
import org.lwjgl.util.vma.*

actual enum class MemoryUsage(override val value: Int) : VmaEnum<MemoryUsage> {
	UNKNOWN(Vma.VMA_MEMORY_USAGE_UNKNOWN),
	GPU_ONLY(Vma.VMA_MEMORY_USAGE_GPU_ONLY),
	CPU_ONLY(Vma.VMA_MEMORY_USAGE_CPU_ONLY),
	CPU_TO_GPU(Vma.VMA_MEMORY_USAGE_CPU_TO_GPU),
	GPU_TO_CPU(Vma.VMA_MEMORY_USAGE_GPU_TO_CPU);
	
	companion object {
		private val enumLookUpMap: Map<Int, MemoryUsage> =
			enumValues<MemoryUsage>().associateBy { it.value }
		
		fun from(value: Int): MemoryUsage = enumLookUpMap[value]!!
	}
}
