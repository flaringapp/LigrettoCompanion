package com.flaringapp.ligretto

import org.gradle.api.Project
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

fun Project.configureComposeMetricsParameters(
    extension: ComposeCompilerGradlePluginExtension,
) {
    val buildDir = layout.buildDirectory

    val enableMetricsProvider = providers.gradleProperty("enableComposeCompilerMetrics")
    if (enableMetricsProvider.orNull == "true") {
        val metricsFolder = buildDir.dir("compose-metrics")
        extension.metricsDestination.set(metricsFolder)
    }

    val enableReportsProvider = providers.gradleProperty("enableComposeCompilerReports")
    if (enableReportsProvider.orNull == "true") {
        val reportsFolder = buildDir.dir("compose-reports")
        extension.reportsDestination.set(reportsFolder)
    }
}
