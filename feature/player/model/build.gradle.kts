plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.player.model"
}
