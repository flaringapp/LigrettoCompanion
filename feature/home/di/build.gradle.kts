plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:home:domain"))
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.home.di"
}
