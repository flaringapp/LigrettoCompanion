package com.flaringapp.ligretto

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

@Suppress("UnusedReceiverParameter")
internal fun Project.configureAndroid(
    extension: CommonExtension,
) = with(extension) {
    compileSdk = 36

    defaultConfig.apply {
        minSdk = 24
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

@Suppress("UnusedReceiverParameter")
internal fun Project.configureAndroidKmp(
    extension: KotlinMultiplatformAndroidLibraryExtension,
) = with(extension) {
    compileSdk = 36
    minSdk = 24

    withHostTest { }
}
