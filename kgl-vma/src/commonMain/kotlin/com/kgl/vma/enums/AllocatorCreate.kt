package com.kgl.vma.enums

import com.kgl.vma.utils.*

expect enum class AllocatorCreate : VmaFlag<AllocatorCreate> {
	EXTERNALLY_SYNCHRONIZED, KHR_DEDICATED_ALLOCATION;
	
	companion object
}
