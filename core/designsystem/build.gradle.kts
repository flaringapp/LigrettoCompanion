plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.core.designsystem"
    }

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
