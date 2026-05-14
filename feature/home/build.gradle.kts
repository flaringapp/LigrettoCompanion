plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.feature.home"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.feature.home.ui)
            api(projects.feature.home.domain)
        }
    }
}
