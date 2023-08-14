@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.koin.ksp")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:database"))
                implementation(project(":core:settings"))
                implementation(project(":feature:game:model"))
                implementation(project(":feature:game:domain-contracts"))
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.datetime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(project(":core:database-test"))
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.multiplatform.settings.test)
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.game.data"
}
