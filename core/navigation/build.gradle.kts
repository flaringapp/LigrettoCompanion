plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.compose.multiplatform.navigation)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.navigation"
}
