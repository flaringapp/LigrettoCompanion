plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.compose.multiplatform.runtime)
            api(libs.compose.multiplatform.ui)

            api(libs.compose.multiplatform.material3)
            api(libs.compose.multiplatform.materialIconsExtended)

            implementation(libs.compose.multiplatform.components.resources)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.designsystem"
}
