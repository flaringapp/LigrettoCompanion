package com.flaringapp.ligretto

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeMultiplatform(
    kotlinExtension: KotlinMultiplatformExtension,
    composeCompilerExtension: ComposeCompilerGradlePluginExtension,
) {
    with(kotlinExtension) {
        // TODO fix for https://youtrack.jetbrains.com/projects/CMP/issues/CMP-9547/Compose-Multiplatform-resources-are-not-packaged-into-the-Android-APK-when-using-AGP-9.0.0-with-the
        configure<KotlinMultiplatformAndroidLibraryTarget> {
            androidResources.enable = true
        }
        sourceSets.apply {
            commonMain.dependencies {
                implementation(libs.compose.multiplatform.uiToolingPreview)
            }
        }
    }

    configureComposeMetricsParameters(composeCompilerExtension)

    dependencies {
        add("androidRuntimeClasspath", libs.compose.multiplatform.uiTooling)
        add("lintChecks", libs.slack.lint.compose)
    }
}
