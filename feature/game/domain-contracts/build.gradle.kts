plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.feature.game.domain.contracts"
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":feature:game:model"))
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}
