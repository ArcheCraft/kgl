package com.kgl.vma.handles

actual class DefragmentationContext(private val allocator: Allocator, internal val handle: Long) {
    actual fun end() {
		allocator.defragmentationEnd(this)
    }
}
