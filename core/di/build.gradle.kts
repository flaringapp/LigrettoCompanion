plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.annotations)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.di"
}
