plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:di"))
            implementation(project(":feature:home:domain"))
            implementation(project(":feature:home:ui"))
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.home.di"
}
