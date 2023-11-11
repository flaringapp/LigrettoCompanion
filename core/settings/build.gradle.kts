plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.koin.ksp")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            api(libs.multiplatform.settings)
            api(libs.multiplatform.settings.coroutines)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.settings"
}
