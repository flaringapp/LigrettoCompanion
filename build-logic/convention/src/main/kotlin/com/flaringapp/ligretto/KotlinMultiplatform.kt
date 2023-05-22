package com.flaringapp.ligretto

import org.gradle.api.JavaVersion
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

@Suppress("UNUSED_VARIABLE")
internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension,
): Unit = with(extension) {
    android {
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
        val commonMain by getting {
            dependencies {
                implementation(libs.findLibrary("napier").get())
            }
        }
        val commonTest by getting {
            dependencies {
                api(libs.findLibrary("kotlin-test").get())
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
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
