plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":feature:game:model"))
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.game.domain.contracts"
}
