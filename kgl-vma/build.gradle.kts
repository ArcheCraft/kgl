import de.undercouch.gradle.tasks.download.*
import org.jetbrains.kotlin.gradle.plugin.mpp.*
import org.jetbrains.kotlin.konan.target.*

plugins {
	kotlin("multiplatform")
	id("de.undercouch.download")
	`maven-publish`
}

val downloadsDir = buildDir.resolve("downloads")
val vmaDir = downloadsDir.resolve("VulkanMemoryAllocator-2.3.0-vs2017/src")

val downloadArchive by tasks.registering(Download::class) {
	src("https://github.com/GPUOpen-LibrariesAndSDKs/VulkanMemoryAllocator/archive/refs/tags/v2.3.0+vs2017.zip")
	dest(downloadsDir.resolve("VulkanMemoryAllocator-2.3.0-vs2017.zip"))

	overwrite(false)
}

val unzipArchive by tasks.registering(Copy::class) {
	dependsOn(downloadArchive)

	from(downloadArchive.map { zipTree(it.dest) })
	into(downloadsDir)
}

val useSingleTarget: Boolean by rootProject.extra
val lwjglVersion: String by rootProject.extra
val lwjglNatives: String by rootProject.extra

kotlin {
	jvm {
		compilations.all {
			kotlinOptions.jvmTarget = "1.8"
		}
	}
	
	evaluationDependsOn(":kgl-vulkan")
	val vulkanUnzipDocs = project(":kgl-vulkan").tasks.named<Copy>("unzipDocs")
	val vulkanHeaderDir = vulkanUnzipDocs.map { it.destinationDir.resolve("include") }

	if (!useSingleTarget || HostManager.hostIsLinux) linuxX64("linux")
	if (!useSingleTarget || HostManager.hostIsMac) macosX64("macos")
	if (!useSingleTarget || HostManager.hostIsMingw) mingwX64("mingw")
	
	targets.withType<KotlinNativeTarget> {
		compilations.named("main") {
			cinterops.create("cvma") {
				tasks.named(interopProcessingTaskName) {
					dependsOn(vulkanUnzipDocs, unzipArchive)
				}
				includeDirs(vulkanHeaderDir, vmaDir)
			}
		}
	}

	sourceSets {
		commonMain {
			dependencies {
				implementation(kotlin("stdlib-common"))
				api(project(":kgl-core"))
				api(project(":kgl-vulkan"))
			}
		}

		commonTest {
			dependencies {
				implementation(kotlin("test-common"))
				implementation(kotlin("test-annotations-common"))
			}
		}

		named("jvmMain") {
			dependencies {
				api("org.lwjgl:lwjgl-vma:$lwjglVersion")
			}
		}

		named("jvmTest") {
			dependencies {
				implementation(kotlin("test-junit"))
				implementation("org.lwjgl:lwjgl:$lwjglVersion:$lwjglNatives")
				implementation("org.lwjgl:lwjgl-vma:$lwjglVersion:$lwjglNatives")
			}
		}

		targets.withType<KotlinNativeTarget> {
			named("${name}Main") {
				kotlin.srcDir("src/nativeMain/kotlin")
				resources.srcDir("src/nativeMain/resources")
			}

			named("${name}Test") {
				kotlin.srcDir("src/nativeTest/kotlin")
				resources.srcDir("src/nativeTest/resources")
			}
		}
	}
}
