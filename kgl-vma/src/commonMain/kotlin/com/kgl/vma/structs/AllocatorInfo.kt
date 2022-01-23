package com.kgl.vma.structs

import com.kgl.vulkan.handles.*

data class AllocatorInfo(val instance: Instance, val physicalDevice: PhysicalDevice, val device: Device)
