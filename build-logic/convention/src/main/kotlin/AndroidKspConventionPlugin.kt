import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidKspConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlin-android").get().get().pluginId)
            apply(libs.findPlugin("ksp").get().get().pluginId)
        }
    }
}
