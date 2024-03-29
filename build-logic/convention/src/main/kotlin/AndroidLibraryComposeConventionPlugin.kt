import com.android.build.gradle.LibraryExtension
import com.flaringapp.ligretto.configureAndroidCompose
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("android-library").get().get().pluginId)
        }

        extensions.configure<LibraryExtension> {
            configureAndroidCompose(this)
        }
    }
}
