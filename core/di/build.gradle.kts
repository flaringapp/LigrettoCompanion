plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.core.di"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.annotations)
        }
    }
}
