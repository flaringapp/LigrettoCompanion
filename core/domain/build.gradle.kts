plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.koin.ksp")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core:model"))
                implementation(project(":core:domain-contracts"))
                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.datetime)
                api(libs.napier)
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.domain"
}
