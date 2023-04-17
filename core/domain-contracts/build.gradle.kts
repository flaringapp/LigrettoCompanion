@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core:model"))
                api(libs.kotlinx.coroutines.core)
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.domain.contracts"
}
