@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.koin.ksp")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                api(libs.multiplatform.settings)
                api(libs.multiplatform.settings.coroutines)
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.settings"
}
