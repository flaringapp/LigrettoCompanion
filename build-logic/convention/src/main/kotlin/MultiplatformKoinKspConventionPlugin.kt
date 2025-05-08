import com.flaringapp.ligretto.alias
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
            alias(libs.plugins.ksp)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(libs.koin.core)
                    implementation(libs.koin.annotations)
                }

                // TODO KSP remove when fixed
                commonMain {
                    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
                }
            }
        }

        extensions.configure<KspExtension> {
            arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
            arg("KOIN_DEFAULT_MODULE", "false")
        }

        dependencies {
            val koin = libs.koin.compiler

            add("kspCommonMainMetadata", koin)

            // TODO KSP workaround below remove when fixed
//            add("kspAndroid", koin)
//            add("kspIosX64", koin)
//            add("kspIosArm64", koin)
//            add("kspIosSimulatorArm64", koin)
        }

        project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
            if (name != "kspCommonMainKotlinMetadata") {
                dependsOn("kspCommonMainKotlinMetadata")
            }
        }
    }
}
