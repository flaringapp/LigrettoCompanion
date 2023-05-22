@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":feature:game:data"))
                implementation(project(":feature:game:domain"))
                implementation(libs.koin.core)
            }
        }
    }
}

android {
    namespace = "com.flaringapp.feature.game.di"
}
