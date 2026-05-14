plugins {
    alias(libs.plugins.ligretto.multiplatform.feature)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.feature.home.ui"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.home.domain)

            implementation(libs.compose.multiplatform.components.resources)
        }
    }
}
