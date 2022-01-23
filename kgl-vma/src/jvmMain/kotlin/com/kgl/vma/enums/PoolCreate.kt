package com.kgl.vma.enums

import com.kgl.vma.utils.*
import org.lwjgl.util.vma.*

actual enum class PoolCreate(override val value: Int) : VmaFlag<PoolCreate> {
	IGNORE_BUFFER_IMAGE_GRANULARITY(Vma.VMA_POOL_CREATE_IGNORE_BUFFER_IMAGE_GRANULARITY_BIT),
	LINEAR_ALGORITHM(Vma.VMA_POOL_CREATE_LINEAR_ALGORITHM_BIT),
	BUDDY_ALGORITHM(Vma.VMA_POOL_CREATE_BUDDY_ALGORITHM_BIT);
	
	actual companion object {
		private val enumLookUpMap: Map<Int, PoolCreate> =
			enumValues<PoolCreate>().associateBy { it.value }
		
		fun fromMultiple(value: Int): VmaFlag<PoolCreate>? = if (value == 0) null else VmaFlag(value)
		
		fun from(value: Int): PoolCreate = enumLookUpMap[value]!!
	}
}

actual val PoolCreate.Companion.ALGORITHM_MASK: VmaFlag<PoolCreate>
	get() = VmaFlag(Vma.VMA_POOL_CREATE_ALGORITHM_MASK)
