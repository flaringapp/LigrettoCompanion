import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
    alias(libs.plugins.ligretto.multiplatform.library.compose)
    alias(libs.plugins.ligretto.multiplatform.koin.compiler)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.common"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.di)
            implementation(projects.core.designsystem)
            implementation(projects.core.ui)
            implementation(projects.core.navigation)
            implementation(projects.core.arch)
            implementation(projects.core.database)
            implementation(projects.core.settings)
            implementation(projects.feature.home)
            implementation(projects.feature.game)

            // Required for navigation3
            implementation(libs.kotlinx.serialization.core)

            implementation(libs.compose.multiplatform.navigation3.ui)
            implementation(libs.compose.multiplatform.lifecycle.viewModel.navigation3)
        }
        commonTest.dependencies {
            implementation(libs.koin.test)
        }
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
    }

    targets.withType<KotlinNativeTarget> {
        binaries.withType<Framework> {
            linkerOpts.add("-lsqlite3")
        }
    }
}

koinCompiler {
    compileSafety.set(true)
}
