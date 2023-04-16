import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class MultiplatformKoinKspConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("ksp").get().get().pluginId)
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
