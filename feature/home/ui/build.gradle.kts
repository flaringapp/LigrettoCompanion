plugins {
    id("ligretto.android.feature")
}

android {
    namespace = "com.flaringapp.ligretto.feature.home.ui"
}

dependencies {
    implementation(project(":feature:game:domain"))
}
