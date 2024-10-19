import com.flaringapp.ligretto.libs
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

class MultiplatformKoinKspConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("ksp").get().get().pluginId)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(libs.findLibrary("koin-core").get())
                    implementation(libs.findLibrary("koin-annotations").get())
                }

                // TODO KSP remove when fixed
                commonMain {
                    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
                }
            }
        }

        extensions.configure<KspExtension> {
            arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
        }

        dependencies {
            val koin = libs.findLibrary("koin-compiler").get()

            add("kspCommonMainMetadata", koin)
            add("kspAndroid", koin)
            add("kspIosX64", koin)
            add("kspIosArm64", koin)
            add("kspIosSimulatorArm64", koin)
        }

        project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
            if (name != "kspCommonMainKotlinMetadata") {
                dependsOn("kspCommonMainKotlinMetadata")
            }
        }
    }
}
