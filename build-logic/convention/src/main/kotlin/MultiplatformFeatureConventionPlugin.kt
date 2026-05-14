import com.flaringapp.ligretto.alias
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            alias(libs.plugins.ligretto.multiplatform.library)
            alias(libs.plugins.ligretto.multiplatform.library.compose)
            alias(libs.plugins.ligretto.multiplatform.koin.compiler)

            // Required for navigation3
            alias(libs.plugins.kotlin.serialization)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(project(":core:util:common"))
                    implementation(project(":core:ui"))
                    implementation(project(":core:designsystem"))
                    api(project(":core:navigation"))
                    implementation(project(":core:arch"))

                    implementation(libs.kotlinx.coroutines.core)

                    // Required for navigation3
                    implementation(libs.kotlinx.serialization.core)

                    implementation(libs.compose.multiplatform.components.resources)

                    implementation(libs.compose.multiplatform.lifecycle.runtime.compose)

                    implementation(libs.compose.multiplatform.navigation3.ui)
                    implementation(libs.compose.multiplatform.navigationEvent)

                    implementation(libs.koin.compose.multiplatform)
                }
            }
        }
    }
}
