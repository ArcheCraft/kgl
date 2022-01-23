package com.kgl.vma.utils

import com.kgl.core.*
import com.kgl.vulkan.utils.*
import cvma.*
import kotlinx.cinterop.*

inline fun <reified STRUCT : CVariable, BUILDER, reified RESULT : CPointed> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (CPointer<STRUCT>, CPointerVar<RESULT>) -> Unit
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.allocPointerTo<RESULT>()
		invoker(pointer.ptr, result)
		return result.pointed!!
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, BUILDER, reified RESULT : CPointed, E1> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, CPointer<STRUCT>, CPointerVar<RESULT>) -> Unit,
	extra1: E1
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.allocPointerTo<RESULT>()
		invoker(extra1, pointer.ptr, result)
		return result.pointed!!
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, BUILDER, reified RESULT : CPointed, E1, E2> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, E2, CPointer<STRUCT>, CPointerVar<RESULT>) -> Unit,
	extra1: E1, extra2: E2
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.allocPointerTo<RESULT>()
		invoker(extra1, extra2, pointer.ptr, result)
		return result.pointed!!
	} finally {
		VirtualStack.pop()
	}
}


inline fun <reified STRUCT : CVariable, BUILDER> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (STRUCT, CPointer<UIntVar>) -> Unit
): UInt {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<UIntVar>()
		invoker(pointer, result.ptr)
		return result.value
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, BUILDER, E1> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, CPointer<STRUCT>, CPointer<UIntVar>) -> Unit,
	extra1: E1
): UInt {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<UIntVar>()
		invoker(extra1, pointer.ptr, result.ptr)
		return result.value
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, BUILDER, E1, E2> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, E2, CPointer<STRUCT>, CPointer<UIntVar>) -> Unit,
	extra1: E1, extra2: E2
): UInt {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<UIntVar>()
		invoker(extra1, extra2, pointer.ptr, result.ptr)
		return result.value
	} finally {
		VirtualStack.pop()
	}
}


inline fun <reified STRUCT : CVariable, BUILDER, reified RESULT : CVariable> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (CPointer<STRUCT>, CPointer<RESULT>) -> VkResult
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<RESULT>()
		invoker(pointer.ptr, result.ptr).let {
			if (it != VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return result
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, BUILDER, reified RESULT : CVariable, E1> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, CPointer<STRUCT>, CPointer<RESULT>) -> VkResult,
	extra1: E1
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<RESULT>()
		invoker(extra1, pointer.ptr, result.ptr).let {
			if (it != VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return result
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, BUILDER, reified RESULT : CVariable, E1, E2> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, E2, CPointer<STRUCT>, CPointer<RESULT>) -> VkResult,
	extra1: E1, extra2: E2
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<RESULT>()
		invoker(extra1, extra2, pointer.ptr, result.ptr).let {
			if (it != VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return result
	} finally {
		VirtualStack.pop()
	}
}


inline fun <reified STRUCT : CVariable, BUILDER> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (CPointer<STRUCT>, CPointer<UIntVar>) -> VkResult
): UInt {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<UIntVar>()
		invoker(pointer.ptr, result.ptr).let {
			if (it != VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return result.value
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, BUILDER, E1> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, CPointer<STRUCT>, CPointer<UIntVar>) -> VkResult,
	extra1: E1
): UInt {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<UIntVar>()
		invoker(extra1, pointer.ptr, result.ptr).let {
			if (it != VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return result.value
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, BUILDER, E1, E2> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, E2, CPointer<STRUCT>, CPointer<UIntVar>) -> VkResult,
	extra1: E1, extra2: E2
): UInt {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<UIntVar>()
		invoker(extra1, extra2, pointer.ptr, result.ptr).let {
			if (it != VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return result.value
	} finally {
		VirtualStack.pop()
	}
}


/*
inline fun <reified STRUCT : CVariable, BUILDER, reified INTERMEDIATE : CVariable, RESULT> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (CPointer<STRUCT>, CPointer<INTERMEDIATE>) -> VkResult,
	resultCreator: (INTERMEDIATE) -> RESULT
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<INTERMEDIATE>()
		invoker(pointer.ptr, result.ptr).let {
			if (it != VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return resultCreator(result)
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, BUILDER, reified INTERMEDIATE : CVariable, RESULT, E1> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, CPointer<STRUCT>, CPointer<INTERMEDIATE>) -> VkResult,
	resultCreator: (INTERMEDIATE) -> RESULT,
	extra1: E1
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<INTERMEDIATE>()
		invoker(extra1, pointer.ptr, result.ptr).let {
			if (it != VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return resultCreator(result)
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, BUILDER, reified INTERMEDIATE : CVariable, RESULT, E1, E2> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, E2, CPointer<STRUCT>, CPointer<INTERMEDIATE>) -> VkResult,
	resultCreator: (INTERMEDIATE) -> RESULT,
	extra1: E1, extra2: E2
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<INTERMEDIATE>()
		invoker(extra1, extra2, pointer.ptr, result.ptr).let {
			if (it != VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return resultCreator(result)
	} finally {
		VirtualStack.pop()
	}
}
*/


inline fun <reified STRUCT : CVariable, BUILDER, reified INTERMEDIATE_VAR : CPointerVarOf<INTERMEDIATE>, reified INTERMEDIATE : CPointer<*>, RESULT> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (CPointer<STRUCT>, CPointer<INTERMEDIATE_VAR>) -> VkResult,
	resultCreator: (INTERMEDIATE) -> RESULT
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<INTERMEDIATE_VAR>()
		invoker(pointer.ptr, result.ptr).let {
			if (it != VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return resultCreator(result.value!!)
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, BUILDER, reified INTERMEDIATE_VAR : CPointerVarOf<INTERMEDIATE>, reified INTERMEDIATE : CPointer<*>, RESULT, E1> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, CPointer<STRUCT>, CPointer<INTERMEDIATE_VAR>) -> VkResult,
	resultCreator: (INTERMEDIATE) -> RESULT,
	extra1: E1
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<INTERMEDIATE_VAR>()
		invoker(extra1, pointer.ptr, result.ptr).let {
			if (it != VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return resultCreator(result.value!!)
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, BUILDER, reified INTERMEDIATE_VAR : CPointerVarOf<INTERMEDIATE>, reified INTERMEDIATE : CPointer<*>, RESULT, E1, E2> getWithVirtualStack(
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, E2, CPointer<STRUCT>, CPointer<INTERMEDIATE_VAR>) -> VkResult,
	resultCreator: (INTERMEDIATE) -> RESULT,
	extra1: E1, extra2: E2
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		val builder = builderCreator(pointer)
		block(builder)
		val result = VirtualStack.alloc<INTERMEDIATE_VAR>()
		invoker(extra1, extra2, pointer.ptr, result.ptr).let {
			if (it != VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return resultCreator(result.value!!)
	} finally {
		VirtualStack.pop()
	}
}


inline fun <reified STRUCT : CVariable, RESULT> getWithVirtualStack(
	invoker: (STRUCT) -> Unit,
	resultCreator: (STRUCT) -> RESULT
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		invoker(pointer)
		return resultCreator(pointer)
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, RESULT, E1> getWithVirtualStack(
	invoker: (E1, STRUCT) -> Unit,
	resultCreator: (STRUCT) -> RESULT,
	extra1: E1
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		invoker(extra1, pointer)
		return resultCreator(pointer)
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, RESULT, E1, E2> getWithVirtualStack(
	invoker: (E1, E2, STRUCT) -> Unit,
	resultCreator: (STRUCT) -> RESULT,
	extra1: E1, extra2: E2
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<STRUCT>()
		invoker(extra1, extra2, pointer)
		return resultCreator(pointer)
	} finally {
		VirtualStack.pop()
	}
}


inline fun <reified STRUCT : CVariable, RESULT> getWithVirtualStack(
	invoker: (CPointerVar<STRUCT>) -> Unit,
	resultCreator: (STRUCT) -> RESULT
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.allocPointerTo<STRUCT>()
		invoker(pointer)
		return resultCreator(pointer.pointed!!)
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, RESULT, E1> getWithVirtualStack(
	invoker: (E1, CPointerVar<STRUCT>) -> Unit,
	resultCreator: (STRUCT) -> RESULT,
	extra1: E1
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.allocPointerTo<STRUCT>()
		invoker(extra1, pointer)
		return resultCreator(pointer.pointed!!)
	} finally {
		VirtualStack.pop()
	}
}

inline fun <reified STRUCT : CVariable, RESULT, E1, E2> getWithVirtualStack(
	invoker: (E1, E2, CPointerVar<STRUCT>) -> Unit,
	resultCreator: (STRUCT) -> RESULT,
	extra1: E1, extra2: E2
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.allocPointerTo<STRUCT>()
		invoker(extra1, extra2, pointer)
		return resultCreator(pointer.pointed!!)
	} finally {
		VirtualStack.pop()
	}
}


inline fun <RESULT> getWithVirtualStack(
	invoker: (CPointer<UIntVar>) -> Unit,
	resultCreator: (UInt) -> RESULT
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<UIntVar>()
		invoker(pointer.ptr)
		return resultCreator(pointer.value)
	} finally {
		VirtualStack.pop()
	}
}

inline fun <RESULT, E1> getWithVirtualStack(
	invoker: (E1, CPointer<UIntVar>) -> Unit,
	resultCreator: (UInt) -> RESULT,
	extra1: E1
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<UIntVar>()
		invoker(extra1, pointer.ptr)
		return resultCreator(pointer.value)
	} finally {
		VirtualStack.pop()
	}
}

inline fun <RESULT, E1, E2> getWithVirtualStack(
	invoker: (E1, E2, CPointer<UIntVar>) -> Unit,
	resultCreator: (UInt) -> RESULT,
	extra1: E1, extra2: E2
): RESULT {
	VirtualStack.push()
	try {
		val pointer = VirtualStack.alloc<UIntVar>()
		invoker(extra1, extra2, pointer.ptr)
		return resultCreator(pointer.value)
	} finally {
		VirtualStack.pop()
	}
}
