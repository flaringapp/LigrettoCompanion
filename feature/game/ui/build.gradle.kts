plugins {
    id("ligretto.android.feature")
}

android {
    namespace = "com.flaringapp.ligretto.feature.game.ui"
}

dependencies {
    implementation(project(":feature:game:domain"))

    implementation(libs.kotlinx.datetime)
}
