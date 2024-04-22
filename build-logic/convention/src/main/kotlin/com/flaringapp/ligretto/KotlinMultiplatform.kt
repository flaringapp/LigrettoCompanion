package com.flaringapp.ligretto

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@OptIn(ExperimentalKotlinGradlePluginApi::class)
internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension,
): Unit = with(extension) {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = this@configureKotlinMultiplatform.resolveFullName()
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets.apply {
        commonMain.dependencies {
            implementation(libs.findLibrary("napier").get())
        }
        commonTest.dependencies {
            implementation(libs.findLibrary("kotlin-test").get())
        }
    }
}

private fun Project.resolveFullName(): String {
    val nonRootParent = parent?.takeIf { it != rootProject } ?: return name
    return "${nonRootParent.resolveFullName()}_$name"
}
