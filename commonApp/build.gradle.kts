import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:di"))
            implementation(project(":core:designsystem"))
            implementation(project(":core:ui"))
            implementation(project(":core:arch"))
            implementation(project(":core:database"))
            implementation(project(":core:settings"))
            implementation(project(":feature:home:ui"))
            implementation(project(":feature:home:di"))
            implementation(project(":feature:game:ui"))
            implementation(project(":feature:game:di"))

            implementation(libs.compose.multiplatform.navigation)

            implementation(libs.koin.core)
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

android {
    namespace = "com.flaringapp.ligretto.common"
}
