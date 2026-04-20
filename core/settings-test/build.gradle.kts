plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.core.settings.test"
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":core:settings"))
            api(libs.multiplatform.settings.test)
        }
    }
}
