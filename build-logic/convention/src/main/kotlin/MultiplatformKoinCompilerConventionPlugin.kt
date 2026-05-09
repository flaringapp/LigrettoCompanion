import com.flaringapp.ligretto.alias
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.koin.compiler.plugin.KoinGradleExtension

class MultiplatformKoinCompilerConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            alias(libs.plugins.koin.compiler)
        }

        extensions.configure<KoinGradleExtension> {
            // TODO enable once cross-module hint analysis is fixed
            // Probably https://github.com/InsertKoinIO/koin-compiler-plugin/issues/6
            // Or probably this should be set to false for all modules except the top most one
            compileSafety.set(false)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(project(":core:di"))
                    implementation(libs.koin.core)
                    implementation(libs.koin.annotations)
                }
            }
        }
    }
}
