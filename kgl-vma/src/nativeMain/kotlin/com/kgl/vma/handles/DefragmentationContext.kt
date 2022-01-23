package com.kgl.vma.handles

import cvma.*

actual class DefragmentationContext(private val allocator: Allocator, internal val handle: VmaDefragmentationContext) {
    actual fun end() {
		allocator.defragmentationEnd(this)
    }
}
