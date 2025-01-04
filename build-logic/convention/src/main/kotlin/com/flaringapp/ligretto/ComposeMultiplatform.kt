package com.flaringapp.ligretto

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.get
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeMultiplatform(
    kotlinExtension: KotlinMultiplatformExtension,
    composeDependencies: ComposePlugin.Dependencies,
    composeCompilerExtension: ComposeCompilerGradlePluginExtension,
    androidExtension: CommonExtension<*, *, *, *, *, *>,
) {
    with(kotlinExtension) {
        sourceSets.apply {
            androidMain.dependencies {
                implementation(project.dependencies.platform(libs.androidx.compose.bom))
                implementation(libs.androidx.compose.ui.preview)
            }
            commonMain.dependencies {
                implementation(composeDependencies.components.uiToolingPreview)
            }
        }
    }

    configureComposeMetricsParameters(composeCompilerExtension)

    with(androidExtension) {
        sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
        sourceSets["main"].res.srcDirs("src/androidMain/res")
        sourceSets["main"].resources.srcDirs("src/commonMain/resources")

        dependencies {
            add("debugImplementation", libs.androidx.compose.ui.debugTooling)
        }
    }

    dependencies {
        add("lintChecks", libs.slack.lint.compose)
    }
}
