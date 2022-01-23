package com.kgl.vma.enums

import com.kgl.vma.utils.*

expect enum class PoolCreate : VmaFlag<PoolCreate> {
	IGNORE_BUFFER_IMAGE_GRANULARITY,
	LINEAR_ALGORITHM,
	BUDDY_ALGORITHM;
	
	companion object
}

expect val PoolCreate.Companion.ALGORITHM_MASK: VmaFlag<PoolCreate>
