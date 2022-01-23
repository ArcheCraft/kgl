package com.kgl.vma.dsls

import com.kgl.vma.enums.*
import com.kgl.vma.utils.*
import org.lwjgl.system.*
import org.lwjgl.util.vma.*

actual class RecordSettingsBuilder(private val target: VmaRecordSettings) {
	init {
	    target.pFilePath(MemoryStack.stackASCII(""))
	}
	
	actual var flags: VmaFlag<Record>?
		get() = Record.fromMultiple(target.flags())
		set(value) { target.flags(value?.value ?: 0) }
	
	actual var filePath: String
		get() = target.pFilePathString()
		set(value) { target.pFilePath(MemoryStack.stackASCII(value)) }
}
