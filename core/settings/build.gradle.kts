plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
    alias(libs.plugins.ligretto.multiplatform.koin.compiler)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.core.settings"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            api(libs.multiplatform.settings)
            api(libs.multiplatform.settings.coroutines)
        }
    }
}
