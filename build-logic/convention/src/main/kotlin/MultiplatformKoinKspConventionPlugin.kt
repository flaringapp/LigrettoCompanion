import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
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

        dependencies {
            val koin = libs.findLibrary("koin-compiler").get()

            add("kspCommonMainMetadata", koin)

            // TODO KSP workaround below remove when fixed
//            add("kspAndroid", koin)
//            add("kspIosX64", koin)
//            add("kspIosArm64", koin)
//            add("kspIosSimulatorArm64", koin)
        }

        // TODO KSP workaround remove when fixed
        tasks.withType<KotlinCompilationTask<*>>().configureEach {
            if (name != "kspCommonMainKotlinMetadata") {
                dependsOn("kspCommonMainKotlinMetadata")
            }
        }
        afterEvaluate {
            tasks.filter {
                it.name.contains("SourcesJar", true)
            }.forEach {
                println("SourceJarTask====>${it.name}")
                it.dependsOn("kspCommonMainKotlinMetadata")
            }
        }
    }
}
