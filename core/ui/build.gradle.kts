plugins {
    id("ligretto.android.library")
    id("ligretto.android.library.compose")
}

android {
    namespace = "com.flaringapp.ligretto.core.ui"
}

dependencies {
    api(project(":core:designsystem"))

    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.preview)
    debugApi(libs.androidx.compose.ui.debugTooling)

    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material.icons)
}
