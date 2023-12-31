plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":core:settings"))
            api(libs.multiplatform.settings.test)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.settings.test"
}
