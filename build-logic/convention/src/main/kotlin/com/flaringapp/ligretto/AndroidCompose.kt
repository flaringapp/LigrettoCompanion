package com.flaringapp.ligretto

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal fun Project.configureAndroidCompose(
    kotlinExtension: KotlinAndroidProjectExtension,
) {
    dependencies {
        add("lintChecks", libs.findLibrary("slack-lint-compose").get())
    }

    with(kotlinExtension) {
        compilerOptions {
            freeCompilerArgs.addAll(buildComposeMetricsParameters())
        }
    }
}
