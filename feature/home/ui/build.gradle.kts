plugins {
    id("ligretto.multiplatform.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:home:domain"))

            implementation(libs.compose.multiplatform.components.resources)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.home.ui"
}
