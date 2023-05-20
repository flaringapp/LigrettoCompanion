@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":feature:game:model"))
                api(libs.kotlinx.coroutines.core)
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.game.domain.contracts"
}
