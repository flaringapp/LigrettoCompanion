plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlinx.datetime)
            }
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.model"
}
