import com.flaringapp.ligretto.configureAndroidCompose
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("android-library").get().get().pluginId)
            apply(libs.findPlugin("compose-multiplatform").get().get().pluginId)
            apply(libs.findPlugin("compose-multiplatform-compiler").get().get().pluginId)
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            configureAndroidCompose(this)
        }
    }
}
