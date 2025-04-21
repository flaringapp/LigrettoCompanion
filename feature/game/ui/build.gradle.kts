plugins {
    id("ligretto.multiplatform.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:game:domain"))

            implementation(libs.kotlinx.datetime)

            implementation(compose.components.resources)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.game.ui"
}
