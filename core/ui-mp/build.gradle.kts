plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":core:designsystem-mp"))

            api(compose.runtime)
            api(compose.ui)
            api(compose.material3)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.ui"
}
