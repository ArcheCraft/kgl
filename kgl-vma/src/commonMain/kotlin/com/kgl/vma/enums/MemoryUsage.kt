package com.kgl.vma.enums

import com.kgl.vma.utils.*

expect enum class MemoryUsage : VmaEnum<MemoryUsage> {
	UNKNOWN,
	GPU_ONLY,
	CPU_ONLY,
	CPU_TO_GPU,
	GPU_TO_CPU
}
