@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core:database"))
                api(project(":core:util:database-test"))
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.database.test"
}
