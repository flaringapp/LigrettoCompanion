plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.feature.home"
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":feature:home:ui"))
            api(project(":feature:home:domain"))
        }
    }
}
