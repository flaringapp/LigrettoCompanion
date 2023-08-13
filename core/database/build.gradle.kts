@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.database")
    id("ligretto.multiplatform.koin.ksp")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.datetime)
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(project(":core:util:database-test"))
                implementation(libs.kotlinx.coroutines.test)
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.database"
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.flaringapp.ligretto.core.database")
            generateAsync.set(true)
        }
    }
}
