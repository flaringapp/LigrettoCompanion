import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
    id("ligretto.multiplatform.koin.compiler")
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.common"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:di"))
            implementation(project(":core:designsystem"))
            implementation(project(":core:ui"))
            implementation(project(":core:arch"))
            implementation(project(":core:database"))
            implementation(project(":core:settings"))
            implementation(project(":feature:home"))
            implementation(project(":feature:game"))

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
