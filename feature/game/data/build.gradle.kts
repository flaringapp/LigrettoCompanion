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
                api(project(":feature:game:domain-contracts"))
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.game.data"
}
