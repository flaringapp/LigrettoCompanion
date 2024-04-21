plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.coroutines.core)

            implementation(compose.runtime)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.arch"
}
