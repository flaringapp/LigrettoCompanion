@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.koin.ksp")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":feature:game:model"))
                implementation(project(":feature:game:domain-contracts"))
                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.datetime)
                api(libs.napier)
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.game.domain"
}
