plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.feature.game"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.feature.game.ui)
            api(projects.feature.game.data)
            api(projects.feature.game.domain)
            api(projects.feature.game.domainContracts)
            api(projects.feature.game.model)
        }
    }
}
