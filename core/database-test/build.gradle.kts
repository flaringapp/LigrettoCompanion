@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core:database"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqlDelight.driver.sqlite)
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.database.test"
}
