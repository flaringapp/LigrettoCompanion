package com.flaringapp.ligretto

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

@Suppress("UnusedReceiverParameter")
internal fun Project.configureKotlinAndroid(
    extension: KotlinAndroidProjectExtension,
) = with(extension) {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}
