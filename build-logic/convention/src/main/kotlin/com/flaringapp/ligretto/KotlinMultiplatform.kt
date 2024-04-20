package com.flaringapp.ligretto

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension,
): Unit = with(extension) {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
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

    targets.all {
        compilations.all {
            compilerOptions.configure {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
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
