import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:designsystem-mp"))
            implementation(project(":core:ui-mp"))
            implementation(project(":core:navigation-mp"))
            implementation(project(":core:arch-mp"))
            implementation(project(":core:database"))
            implementation(project(":core:settings"))
            implementation(project(":feature:home:ui"))
            implementation(project(":feature:home:di"))
            implementation(project(":feature:game:ui"))
            implementation(project(":feature:game:di"))

            implementation(libs.koin.core)
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

android {
    namespace = "com.flaringapp.ligretto.common"
}
