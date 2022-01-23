package com.kgl.vma.enums

import com.kgl.vma.utils.*

expect enum class AllocationCreate : VmaFlag<AllocationCreate> {
	DEDICATED_MEMORY,
	NEVER_ALLOCATE,
	MAPPED,
	CAN_BECOME_LOST,
	CAN_MAKE_OTHER_LOST,
	USER_DATA_COPY_STRING,
	UPPER_ADDRESS,
	DONT_BIND,
	STRATEGY_BEST_FIT,
	STRATEGY_WORST_FIT,
	STRATEGY_FIRST_FIT,
	STRATEGY_MIN_MEMORY,
	STRATEGY_MIN_TIME,
	STRATEGY_MIN_FRAGMENTATION;
	
	companion object
}

expect val AllocationCreate.Companion.STRATEGY_MASK: VmaFlag<AllocationCreate>
