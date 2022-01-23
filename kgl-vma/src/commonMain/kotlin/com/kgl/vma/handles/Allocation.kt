package com.kgl.vma.handles

import com.kgl.vma.structs.*
import com.kgl.vma.utils.*
import com.kgl.vulkan.handles.*
import io.ktor.utils.io.bits.*

expect class Allocation : VmaHandle {
	val info: AllocationInfo
	
	
	fun touch()
	
	fun map(): Memory
	
	fun unmap()
	
	fun flush(offset: ULong, size: ULong)
	
	fun invalidate(offset: ULong, size: ULong)
	
	fun bindBufferMemory(buffer: Buffer)
	
	fun bindImageMemory(image: Image)
}
