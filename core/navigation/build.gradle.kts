plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
    alias(libs.plugins.ligretto.multiplatform.library.compose)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.core.navigation"
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.compose.multiplatform.runtime)
            api(libs.compose.multiplatform.navigation3.runtime)

            implementation(libs.kotlinx.serialization.core)
        }
    }
}
