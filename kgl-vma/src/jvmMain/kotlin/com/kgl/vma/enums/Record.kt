package com.kgl.vma.enums

import com.kgl.vma.utils.*
import org.lwjgl.util.vma.*

actual enum class Record(override val value: Int) : VmaFlag<Record> {
    FLUSH_AFTER_CALL(Vma.VMA_RECORD_FLUSH_AFTER_CALL_BIT);
	
	actual companion object {
		private val enumLookUpMap: Map<Int, Record> =
			enumValues<Record>().associateBy { it.value }
		
		fun fromMultiple(value: Int): VmaFlag<Record>? = if (value == 0) null else VmaFlag(value)
		
		fun from(value: Int): Record = enumLookUpMap[value]!!
	}
}
