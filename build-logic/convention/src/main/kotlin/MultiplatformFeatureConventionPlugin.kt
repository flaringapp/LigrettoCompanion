import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("ligretto.multiplatform.library")
            apply("ligretto.multiplatform.library.compose")
            apply("ligretto.multiplatform.koin.ksp")
        }

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(project(":core:ui-mp"))
                    implementation(project(":core:designsystem-mp"))
                    implementation(project(":core:arch-mp"))

                    val compose = extensions.getByType<ComposeExtension>()
                    implementation(compose.dependencies.components.resources)

                    implementation(libs.findLibrary("kotlinx-coroutines-core").get())

                    implementation(
                        libs.findLibrary("lifecycle-multiplatform-runtime-compose").get()
                    )

                    implementation(libs.findLibrary("koin-compose-multiplatform").get())

                    implementation(libs.findLibrary("kmp-viewModel-compose-koin").get())
                }
                androidMain.dependencies {
                    implementation(project(":core:navigation"))
                }
            }
        }
    }
}
