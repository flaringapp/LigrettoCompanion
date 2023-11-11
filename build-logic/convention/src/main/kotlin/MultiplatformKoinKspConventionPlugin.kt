import com.flaringapp.ligretto.libs
import com.flaringapp.ligretto.sourceSets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformKoinKspConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("ksp").get().get().pluginId)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets {
                commonMain.dependencies {
                    implementation(libs.findLibrary("koin-core").get())
                    implementation(libs.findLibrary("koin-annotations").get())
                }
            }
        }

        dependencies {
            val koin = libs.findLibrary("koin-compiler").get()

            add("kspCommonMainMetadata", koin)
            add("kspAndroid", koin)
            add("kspIosX64", koin)
            add("kspIosArm64", koin)
            add("kspIosSimulatorArm64", koin)
        }
    }
}
