plugins {
    id("ligretto.multiplatform.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:home:domain"))

            implementation(compose.components.resources)

            implementation(libs.compose.multiplatform.adaptive)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.home.ui"
}
