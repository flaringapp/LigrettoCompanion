plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:game:data"))
            implementation(project(":feature:game:domain"))
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.game.di"
}
