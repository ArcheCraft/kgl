package com.kgl.vma.structs

data class Stats(val total: StatInfo, val memoryTypes: Array<StatInfo>, val memoryHeaps: Array<StatInfo>) {
	fun memoryType(memoryTypeIndex: UInt): StatInfo {
		return memoryTypes[memoryTypeIndex.toInt()]
	}
	
	fun memoryHeap(memoryHeapIndex: UInt): StatInfo {
		return memoryHeaps[memoryHeapIndex.toInt()]
	}
	
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || this::class != other::class) return false
		
		other as Stats
		
		if (total != other.total) return false
		if (!memoryTypes.contentEquals(other.memoryTypes)) return false
		if (!memoryHeaps.contentEquals(other.memoryHeaps)) return false
		
		return true
	}
	
	override fun hashCode(): Int {
		var result = total.hashCode()
		result = 31 * result + memoryTypes.contentHashCode()
		result = 31 * result + memoryHeaps.contentHashCode()
		return result
	}
	
	companion object
}
