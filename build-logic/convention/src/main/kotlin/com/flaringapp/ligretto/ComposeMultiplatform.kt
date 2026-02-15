package com.flaringapp.ligretto

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeMultiplatform(
    kotlinExtension: KotlinMultiplatformExtension,
    composeCompilerExtension: ComposeCompilerGradlePluginExtension,
    androidExtension: CommonExtension<*, *, *, *, *, *>,
) {
    with(kotlinExtension) {
        sourceSets.apply {
            commonMain.dependencies {
                implementation(libs.compose.multiplatform.uiToolingPreview)
            }
        }
    }

    configureComposeMetricsParameters(composeCompilerExtension)

    with(androidExtension) {
        sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
        sourceSets["main"].res.srcDirs("src/androidMain/res")
        sourceSets["main"].resources.srcDirs("src/commonMain/resources")

        dependencies {
            add("debugImplementation", libs.compose.multiplatform.uiTooling)
        }
    }

    dependencies {
        add("lintChecks", libs.slack.lint.compose)
    }
}
