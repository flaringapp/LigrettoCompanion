plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":feature:player:model"))

            implementation(libs.kotlinx.datetime)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.game.model"
}
