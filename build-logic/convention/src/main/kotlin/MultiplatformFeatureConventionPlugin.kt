import com.flaringapp.ligretto.alias
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("ligretto.multiplatform.library")
            apply("ligretto.multiplatform.library.compose")
            apply("ligretto.multiplatform.koin.ksp")

            // Required for type safe navigation
            alias(libs.plugins.kotlin.serialization)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(project(":core:util:common"))
                    implementation(project(":core:ui"))
                    implementation(project(":core:designsystem"))
                    implementation(project(":core:arch"))

                    implementation(libs.kotlinx.coroutines.core)

                    // Required for type safe navigation
                    implementation(libs.kotlinx.serialization.json)

                    val composeDependencies = extensions.getByType<ComposePlugin.Dependencies>()
                    implementation(composeDependencies.components.resources)

                    implementation(libs.compose.multiplatform.lifecycle.runtime.compose)

                    implementation(libs.compose.multiplatform.navigation)

                    implementation(libs.koin.compose.multiplatform)
                }
            }
        }
    }
}
