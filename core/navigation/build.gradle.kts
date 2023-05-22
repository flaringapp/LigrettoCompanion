plugins {
    id("ligretto.android.library")
    id("ligretto.android.library.compose")
}

android {
    namespace = "com.flaringapp.ligretto.core.navigation"
}

dependencies {
    api(libs.androidx.navigation.compose)
}
