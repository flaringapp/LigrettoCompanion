plugins {
    id("ligretto.android.library")
    id("ligretto.android.library.compose")
}

android {
    namespace = "com.flaringapp.ligretto.core.arch"
}

dependencies {
    api(libs.kotlinx.coroutines.android)

    api(libs.androidx.lifecycle.core)
    api(libs.androidx.lifecycle.viewModel)

    api(libs.androidx.compose.runtime)
}
