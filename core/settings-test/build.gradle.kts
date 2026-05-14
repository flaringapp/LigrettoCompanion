plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.core.settings.test"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.core.settings)
            api(libs.multiplatform.settings.test)
        }
    }
}
