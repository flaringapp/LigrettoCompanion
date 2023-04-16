@file:Suppress("UnstableApiUsage")

// https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id("ligretto.android.application.compose")
}

android {
    namespace = "com.flaringapp.ligretto.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.flaringapp.ligretto.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    androidComponents.onVariants { variant ->
        kotlin.sourceSets.findByName(variant.name)?.kotlin?.srcDirs(
            file("$buildDir/generated/ksp/${variant.name}/kotlin")
        )
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

    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    ksp(libs.koin.compiler)
}
