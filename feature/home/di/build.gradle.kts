plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.feature.home.di"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:di"))
            implementation(project(":feature:home:domain"))
            implementation(project(":feature:home:ui"))
            implementation(libs.koin.core)
        }
    }
}
