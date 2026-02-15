plugins {
    id("ligretto.multiplatform.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:game:domain"))

            implementation(libs.kotlinx.datetime)

            implementation(libs.compose.multiplatform.components.resources)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.game.ui"
}
