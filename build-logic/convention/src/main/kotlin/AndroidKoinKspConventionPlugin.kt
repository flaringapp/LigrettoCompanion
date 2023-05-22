import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidKoinKspConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlin-android").get().get().pluginId)
            apply(libs.findPlugin("ksp").get().get().pluginId)
        }

        dependencies {
            add("implementation", libs.findLibrary("koin-android").get())
            add("implementation", libs.findLibrary("koin-compose").get())
            add("implementation", libs.findLibrary("koin-annotations").get())
            add("ksp", libs.findLibrary("koin-compiler").get())
        }
    }
}
