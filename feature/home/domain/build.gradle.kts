plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
    alias(libs.plugins.ligretto.multiplatform.koin.compiler)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.feature.home.domain"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.feature.game.model)
            implementation(projects.feature.game.domainContracts)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}
