plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.koin.ksp")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":feature:player:model"))

            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.player.domain"
}
