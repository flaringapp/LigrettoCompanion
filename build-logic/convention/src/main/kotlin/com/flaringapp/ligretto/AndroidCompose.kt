package com.flaringapp.ligretto

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureAndroidCompose(
    composeCompilerExtension: ComposeCompilerGradlePluginExtension,
) {
    dependencies {
        add("lintChecks", libs.findLibrary("slack-lint-compose").get())
    }

    configureComposeMetricsParameters(composeCompilerExtension)
}
