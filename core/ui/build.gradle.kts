plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":core:designsystem"))

            api(libs.compose.multiplatform.runtime)
            api(libs.compose.multiplatform.ui)
            api(libs.compose.multiplatform.material3)

            implementation(libs.compose.multiplatform.components.resources)
        }
    }
}

compose.resources {
    publicResClass = true
}

android {
    namespace = "com.flaringapp.ligretto.core.ui"
}
