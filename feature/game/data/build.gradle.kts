plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
    alias(libs.plugins.ligretto.multiplatform.koin.compiler)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.feature.game.data"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.database)
            implementation(projects.core.settings)
            implementation(projects.feature.game.model)
            implementation(projects.feature.game.domainContracts)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(projects.core.databaseTest)
            implementation(projects.core.settingsTest)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}
