package com.kgl.vma.dsls

import com.kgl.core.*
import com.kgl.vma.enums.*
import com.kgl.vma.utils.*
import cvma.*
import kotlinx.cinterop.*


actual class RecordSettingsBuilder(private val target: VmaRecordSettings) {
	actual var flags: VmaFlag<Record>?
		get() = Record.fromMultiple(target.flags)
		set(value) { target.flags = value?.value ?: 0u }
	
	actual var filePath: String
		get() = target.pFilePath?.toKString() ?: ""
		set(value) { target.pFilePath = value.cstr.placeTo(VirtualStack.currentFrame!!) }
}
