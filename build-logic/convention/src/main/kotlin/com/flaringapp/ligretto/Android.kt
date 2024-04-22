package com.flaringapp.ligretto

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

@Suppress("UnusedReceiverParameter")
internal fun Project.configureAndroid(
    extension: CommonExtension<*, *, *, *, *, *>,
) = with(extension) {
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
