plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:di"))
            implementation(project(":feature:game:data"))
            implementation(project(":feature:game:domain"))
            implementation(project(":feature:game:ui"))
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.game.di"
}
