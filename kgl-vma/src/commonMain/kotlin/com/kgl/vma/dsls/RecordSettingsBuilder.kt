package com.kgl.vma.dsls

import com.kgl.vma.enums.*
import com.kgl.vma.utils.*

expect class RecordSettingsBuilder {
	var flags: VmaFlag<Record>?
	
	var filePath: String
}
