package com.kgl.vma.enums

import com.kgl.vma.utils.*
import cvma.*

actual enum class Record(override val value: UInt) : VmaFlag<Record> {
    FLUSH_AFTER_CALL(VMA_RECORD_FLUSH_AFTER_CALL_BIT);
	
	actual companion object {
		private val enumLookUpMap: Map<UInt, Record> =
			enumValues<Record>().associateBy { it.value }
		
		fun fromMultiple(value: UInt): VmaFlag<Record>? = if (value == 0u) null else VmaFlag(value)
		
		fun from(value: UInt): Record = enumLookUpMap[value]!!
	}
}
