plugins {
    id("ligretto.android.application")
    id("ligretto.android.application.compose")
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
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    lint {
        checkAllWarnings = true
        checkDependencies = true
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":commonApp"))

    implementation(libs.androidx.core.splashScreen)

    implementation(libs.androidx.activity.compose)

    implementation(compose.foundation)
}
