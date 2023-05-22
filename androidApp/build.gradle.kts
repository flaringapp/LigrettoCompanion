@file:Suppress("UnstableApiUsage")

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
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    lint {
        checkAllWarnings = true
        checkDependencies = true
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:arch"))
    implementation(project(":feature:home:ui"))
    implementation(project(":feature:game:ui"))
    implementation(project(":feature:game:di"))

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.core.splashScreen)

    implementation(libs.androidx.activity.compose)

    implementation(libs.google.accompanist.systemUiController)
}
