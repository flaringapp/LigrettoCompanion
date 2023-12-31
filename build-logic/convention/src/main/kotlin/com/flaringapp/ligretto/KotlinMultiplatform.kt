package com.flaringapp.ligretto

import org.gradle.api.JavaVersion
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

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

    sourceSets {
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

internal fun KotlinMultiplatformExtension.sourceSets(
    configure: NamedDomainObjectContainer<KotlinSourceSet>.() -> Unit
) {
    (this as ExtensionAware).extensions.configure("sourceSets", configure)
}
