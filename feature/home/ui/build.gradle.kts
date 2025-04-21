plugins {
    id("ligretto.multiplatform.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:home:domain"))

            implementation(compose.components.resources)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.home.ui"
}
