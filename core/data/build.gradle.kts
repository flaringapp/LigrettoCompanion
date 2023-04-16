plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.koin.ksp")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:model"))
                implementation(project(":core:domain-contracts"))
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.data"
}
