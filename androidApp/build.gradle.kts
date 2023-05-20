plugins {
    id("ligretto.android.application")
    id("ligretto.android.application.compose")
    id("ligretto.android.ksp")
    id("ligretto.android.koin.ksp")
}

android {
    namespace = "com.flaringapp.ligretto.android"
    defaultConfig {
        applicationId = "com.flaringapp.ligretto.android"
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    lint {
        checkAllWarnings = true
        checkDependencies = true
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data"))

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashScreen)
    implementation(libs.androidx.lifecycle.core)
    implementation(libs.androidx.lifecycle.viewModel)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.liveData)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.preview)
    debugImplementation(libs.androidx.compose.ui.debugTooling)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons)

    implementation(libs.google.accompanist.systemUiController)

    implementation(libs.koin.compose)
}
