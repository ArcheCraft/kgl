package com.kgl.vma.handles

import com.kgl.vma.structs.*
import com.kgl.vma.utils.*

expect class Pool : VmaHandle {
	val stats: PoolStats
	
	
	fun makeAllocationsLost()
	
	fun checkPoolCorruption(): Boolean
}
