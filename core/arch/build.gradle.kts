plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.core.arch"
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.coroutines.core)

            implementation(libs.compose.multiplatform.runtime)

            implementation(libs.compose.multiplatform.lifecycle.viewModel)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
    }
}
