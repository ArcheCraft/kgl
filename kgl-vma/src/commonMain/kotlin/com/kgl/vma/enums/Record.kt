package com.kgl.vma.enums

import com.kgl.vma.utils.*

expect enum class Record : VmaFlag<Record> {
	FLUSH_AFTER_CALL;
	
	companion object
}
