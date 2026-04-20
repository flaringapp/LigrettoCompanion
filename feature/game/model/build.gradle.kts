plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.feature.game.model"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.datetime)
        }
    }
}
