import com.android.build.gradle.LibraryExtension
import com.flaringapp.ligretto.configureComposeMultiplatform
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class MultiplatformComposeLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlin-multiplatform").get().get().pluginId)
            apply(libs.findPlugin("compose-multiplatform").get().get().pluginId)
            apply(libs.findPlugin("compose-multiplatform-compiler").get().get().pluginId)
        }

        configureComposeMultiplatform(
            kotlinExtension = extensions.getByType(),
            composeExtension = extensions.getByType(),
            androidExtension = extensions.getByType<LibraryExtension>(),
        )
    }
}
