package com.kgl.vma.enums

import com.kgl.vma.utils.*
import cvma.*

actual enum class AllocatorCreate(override val value: UInt) : VmaFlag<AllocatorCreate> {
    EXTERNALLY_SYNCHRONIZED(VMA_ALLOCATOR_CREATE_EXTERNALLY_SYNCHRONIZED_BIT), KHR_DEDICATED_ALLOCATION(VMA_ALLOCATOR_CREATE_KHR_DEDICATED_ALLOCATION_BIT);
	
	actual companion object {
		private val enumLookUpMap: Map<UInt, AllocatorCreate> =
			enumValues<AllocatorCreate>().associateBy { it.value }
		
		fun fromMultiple(value: UInt): VmaFlag<AllocatorCreate>? = if (value == 0u) null else VmaFlag(value)
		
		fun from(value: UInt): AllocatorCreate = enumLookUpMap[value]!!
	}
}
