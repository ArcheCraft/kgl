package com.kgl.vma.enums

import com.kgl.vma.utils.*
import cvma.*


actual enum class PoolCreate(override val value: UInt) : VmaFlag<PoolCreate> {
	IGNORE_BUFFER_IMAGE_GRANULARITY(VMA_POOL_CREATE_IGNORE_BUFFER_IMAGE_GRANULARITY_BIT),
	LINEAR_ALGORITHM(VMA_POOL_CREATE_LINEAR_ALGORITHM_BIT),
	BUDDY_ALGORITHM(VMA_POOL_CREATE_BUDDY_ALGORITHM_BIT);
	
	actual companion object {
		private val enumLookUpMap: Map<UInt, PoolCreate> =
			enumValues<PoolCreate>().associateBy { it.value }
		
		fun fromMultiple(value: UInt): VmaFlag<PoolCreate>? = if (value == 0u) null else VmaFlag(value)
		
		fun from(value: UInt): PoolCreate = enumLookUpMap[value]!!
	}
}

actual val PoolCreate.Companion.ALGORITHM_MASK: VmaFlag<PoolCreate>
	get() = VmaFlag(VMA_POOL_CREATE_ALGORITHM_MASK)
