plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.ui)

            api(compose.material3)
            api(compose.materialIconsExtended)

            implementation(compose.components.resources)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.designsystem"
}
