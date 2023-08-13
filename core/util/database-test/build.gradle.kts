@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        val androidMain by getting {
            dependencies {
                api(libs.kotlinx.coroutines.core)
                implementation(libs.sqlDelight.driver.sqlite)
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.util.database.test"
}
