package com.kgl.vma.utils

import com.kgl.vulkan.utils.*
import org.lwjgl.*
import org.lwjgl.system.*
import org.lwjgl.vulkan.*
import java.nio.*

fun <STRUCT : Struct, BUILDER, RESULT> getWithMemoryStack(
	structCreator: () -> STRUCT,
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (STRUCT, PointerBuffer) -> Unit,
	resultCreator: (Long) -> RESULT
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = structCreator()
		val builder = builderCreator(pointer)
		block(builder)
		val result = MemoryStack.stackCallocPointer(1)
		invoker(pointer, result)
		return resultCreator(result[0])
	} finally {
		MemoryStack.stackPop()
	}
}

fun <STRUCT : Struct, BUILDER, RESULT, E1> getWithMemoryStack(
	structCreator: () -> STRUCT,
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, STRUCT, PointerBuffer) -> Unit,
	resultCreator: (Long) -> RESULT,
	extra1: E1
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = structCreator()
		val builder = builderCreator(pointer)
		block(builder)
		val result = MemoryStack.stackCallocPointer(1)
		invoker(extra1, pointer, result)
		return resultCreator(result[0])
	} finally {
		MemoryStack.stackPop()
	}
}

fun <STRUCT : Struct, BUILDER, RESULT, E1, E2> getWithMemoryStack(
	structCreator: () -> STRUCT,
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, E2, STRUCT, PointerBuffer) -> Unit,
	resultCreator: (Long) -> RESULT,
	extra1: E1, extra2: E2
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = structCreator()
		val builder = builderCreator(pointer)
		block(builder)
		val result = MemoryStack.stackCallocPointer(1)
		invoker(extra1, extra2, pointer, result)
		return resultCreator(result[0])
	} finally {
		MemoryStack.stackPop()
	}
}


@JvmName("getWithMemoryStackWithResult")
fun <STRUCT : Struct, BUILDER, RESULT> getWithMemoryStack(
	structCreator: () -> STRUCT,
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (STRUCT, PointerBuffer) -> Int,
	resultCreator: (Long) -> RESULT
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = structCreator()
		val builder = builderCreator(pointer)
		block(builder)
		val result = MemoryStack.stackCallocPointer(1)
		invoker(pointer, result).let {
			if (it != VK10.VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return resultCreator(result[0])
	} finally {
		MemoryStack.stackPop()
	}
}

@JvmName("getWithMemoryStackWithResult")
fun <STRUCT : Struct, BUILDER, RESULT, E1> getWithMemoryStack(
	structCreator: () -> STRUCT,
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, STRUCT, PointerBuffer) -> Int,
	resultCreator: (Long) -> RESULT,
	extra1: E1
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = structCreator()
		val builder = builderCreator(pointer)
		block(builder)
		val result = MemoryStack.stackCallocPointer(1)
		invoker(extra1, pointer, result).let {
			if (it != VK10.VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return resultCreator(result[0])
	} finally {
		MemoryStack.stackPop()
	}
}

@JvmName("getWithMemoryStackWithResult")
fun <STRUCT : Struct, BUILDER, RESULT, E1, E2> getWithMemoryStack(
	structCreator: () -> STRUCT,
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, E2, STRUCT, PointerBuffer) -> Int,
	resultCreator: (Long) -> RESULT,
	extra1: E1, extra2: E2
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = structCreator()
		val builder = builderCreator(pointer)
		block(builder)
		val result = MemoryStack.stackCallocPointer(1)
		invoker(extra1, extra2, pointer, result).let {
			if (it != VK10.VK_SUCCESS) {
				handleVkResult(it)
			}
		}
		return resultCreator(result[0])
	} finally {
		MemoryStack.stackPop()
	}
}


@JvmName("getWithMemoryStackInt")
fun <STRUCT : Struct, BUILDER, RESULT> getWithMemoryStack(
	structCreator: () -> STRUCT,
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (STRUCT, IntBuffer) -> Unit,
	resultCreator: (Int) -> RESULT
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = structCreator()
		val builder = builderCreator(pointer)
		block(builder)
		val result = MemoryStack.stackCallocInt(1)
		invoker(pointer, result)
		return resultCreator(result[0])
	} finally {
		MemoryStack.stackPop()
	}
}

@JvmName("getWithMemoryStackInt")
fun <STRUCT : Struct, BUILDER, RESULT, E1> getWithMemoryStack(
	structCreator: () -> STRUCT,
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, STRUCT, IntBuffer) -> Unit,
	resultCreator: (Int) -> RESULT,
	extra1: E1
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = structCreator()
		val builder = builderCreator(pointer)
		block(builder)
		val result = MemoryStack.stackCallocInt(1)
		invoker(extra1, pointer, result)
		return resultCreator(result[0])
	} finally {
		MemoryStack.stackPop()
	}
}

@JvmName("getWithMemoryStackInt")
fun <STRUCT : Struct, BUILDER, RESULT, E1, E2> getWithMemoryStack(
	structCreator: () -> STRUCT,
	builderCreator: (STRUCT) -> BUILDER,
	block: (BUILDER) -> Unit,
	invoker: (E1, E2, STRUCT, IntBuffer) -> Unit,
	resultCreator: (Int) -> RESULT,
	extra1: E1, extra2: E2
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = structCreator()
		val builder = builderCreator(pointer)
		block(builder)
		val result = MemoryStack.stackCallocInt(1)
		invoker(extra1, extra2, pointer, result)
		return resultCreator(result[0])
	} finally {
		MemoryStack.stackPop()
	}
}


fun <STRUCT : Struct, RESULT> getWithMemoryStack(
	structCreator: () -> STRUCT,
	invoker: (STRUCT) -> Unit,
	resultCreator: (STRUCT) -> RESULT
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = structCreator()
		invoker(pointer)
		return resultCreator(pointer)
	} finally {
		MemoryStack.stackPop()
	}
}

fun <STRUCT : Struct, RESULT, E1> getWithMemoryStack(
	structCreator: () -> STRUCT,
	invoker: (E1, STRUCT) -> Unit,
	resultCreator: (STRUCT) -> RESULT,
	extra1: E1
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = structCreator()
		invoker(extra1, pointer)
		return resultCreator(pointer)
	} finally {
		MemoryStack.stackPop()
	}
}

fun <STRUCT : Struct, RESULT, E1, E2> getWithMemoryStack(
	structCreator: () -> STRUCT,
	invoker: (E1, E2, STRUCT) -> Unit,
	resultCreator: (STRUCT) -> RESULT,
	extra1: E1, extra2: E2
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = structCreator()
		invoker(extra1, extra2, pointer)
		return resultCreator(pointer)
	} finally {
		MemoryStack.stackPop()
	}
}


fun <RESULT> getWithMemoryStack(
	invoker: (PointerBuffer) -> Unit,
	resultCreator: (Long) -> RESULT
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = MemoryStack.stackCallocPointer(1)
		invoker(pointer)
		return resultCreator(pointer[0])
	} finally {
		MemoryStack.stackPop()
	}
}

fun <RESULT, E1> getWithMemoryStack(
	invoker: (E1, PointerBuffer) -> Unit,
	resultCreator: (Long) -> RESULT,
	extra1: E1
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = MemoryStack.stackCallocPointer(1)
		invoker(extra1, pointer)
		return resultCreator(pointer[0])
	} finally {
		MemoryStack.stackPop()
	}
}

fun <RESULT, E1, E2> getWithMemoryStack(
	invoker: (E1, E2, PointerBuffer) -> Unit,
	resultCreator: (Long) -> RESULT,
	extra1: E1, extra2: E2
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = MemoryStack.stackCallocPointer(1)
		invoker(extra1, extra2, pointer)
		return resultCreator(pointer[0])
	} finally {
		MemoryStack.stackPop()
	}
}


@JvmName("getWithMemoryStackInt")
fun <RESULT> getWithMemoryStack(
	invoker: (IntBuffer) -> Unit,
	resultCreator: (Int) -> RESULT
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = MemoryStack.stackCallocInt(1)
		invoker(pointer)
		return resultCreator(pointer[0])
	} finally {
		MemoryStack.stackPop()
	}
}

@JvmName("getWithMemoryStackInt")
fun <RESULT, E1> getWithMemoryStack(
	invoker: (E1, IntBuffer) -> Unit,
	resultCreator: (Int) -> RESULT,
	extra1: E1
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = MemoryStack.stackCallocInt(1)
		invoker(extra1, pointer)
		return resultCreator(pointer[0])
	} finally {
		MemoryStack.stackPop()
	}
}

@JvmName("getWithMemoryStackInt")
fun <RESULT, E1, E2> getWithMemoryStack(
	invoker: (E1, E2, IntBuffer) -> Unit,
	resultCreator: (Int) -> RESULT,
	extra1: E1, extra2: E2
): RESULT {
	MemoryStack.stackPush()
	try {
		val pointer = MemoryStack.stackCallocInt(1)
		invoker(extra1, extra2, pointer)
		return resultCreator(pointer[0])
	} finally {
		MemoryStack.stackPop()
	}
}
