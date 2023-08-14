@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core:settings"))
                api(libs.multiplatform.settings.test)
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.settings.test"
}
