plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)

            implementation(libs.compose.multiplatform.lifecycle.viewModel)

            implementation(libs.koin.compose.multiplatform)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.arch.koin"
}
