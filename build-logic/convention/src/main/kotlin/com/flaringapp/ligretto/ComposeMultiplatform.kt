package com.flaringapp.ligretto

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeMultiplatform(
    kotlinExtension: KotlinMultiplatformExtension,
    composeCompilerExtension: ComposeCompilerGradlePluginExtension,
) {
    with(kotlinExtension) {
        sourceSets.apply {
            commonMain.dependencies {
                implementation(libs.compose.multiplatform.uiToolingPreview)
            }
        }
    }

    configureComposeMetricsParameters(composeCompilerExtension)

    dependencies {
        add("debugImplementation", libs.compose.multiplatform.uiTooling)
        add("lintChecks", libs.slack.lint.compose)
    }
}
