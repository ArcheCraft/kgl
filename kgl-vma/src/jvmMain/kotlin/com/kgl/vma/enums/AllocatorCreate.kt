package com.kgl.vma.enums

import com.kgl.vma.utils.*
import org.lwjgl.util.vma.*

actual enum class AllocatorCreate(override val value: Int) : VmaFlag<AllocatorCreate> {
    EXTERNALLY_SYNCHRONIZED(Vma.VMA_ALLOCATOR_CREATE_EXTERNALLY_SYNCHRONIZED_BIT), KHR_DEDICATED_ALLOCATION(Vma.VMA_ALLOCATOR_CREATE_KHR_DEDICATED_ALLOCATION_BIT);
	
	actual companion object {
		private val enumLookUpMap: Map<Int, AllocatorCreate> =
			enumValues<AllocatorCreate>().associateBy { it.value }
		
		fun fromMultiple(value: Int): VmaFlag<AllocatorCreate>? = if (value == 0) null else VmaFlag(value)
		
		fun from(value: Int): AllocatorCreate = enumLookUpMap[value]!!
	}
}
