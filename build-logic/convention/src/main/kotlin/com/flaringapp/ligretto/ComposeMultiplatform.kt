package com.flaringapp.ligretto

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.get
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeMultiplatform(
    kotlinExtension: KotlinMultiplatformExtension,
    composeExtension: ComposeExtension,
    composeCompilerExtension: ComposeCompilerGradlePluginExtension,
    androidExtension: CommonExtension<*, *, *, *, *, *>,
) {
    with(kotlinExtension) {
        sourceSets.apply {
            androidMain.dependencies {
                val bom = libs.findLibrary("androidx-compose-bom").get()
                implementation(project.dependencies.platform(bom))
                implementation(libs.findLibrary("androidx-compose-ui-preview").get())
            }
            commonMain.dependencies {
                implementation(composeExtension.dependencies.components.uiToolingPreview)
            }
        }
    }

    configureComposeMetricsParameters(composeCompilerExtension)

    with(androidExtension) {
        sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
        sourceSets["main"].res.srcDirs("src/androidMain/res")
        sourceSets["main"].resources.srcDirs("src/commonMain/resources")

        dependencies {
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-debugTooling").get())
        }
    }

    dependencies {
        add("lintChecks", libs.findLibrary("slack-lint-compose").get())
    }
}
