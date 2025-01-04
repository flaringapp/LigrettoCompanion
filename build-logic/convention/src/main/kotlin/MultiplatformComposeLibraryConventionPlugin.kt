import com.android.build.gradle.LibraryExtension
import com.flaringapp.ligretto.alias
import com.flaringapp.ligretto.configureComposeMultiplatform
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension

class MultiplatformComposeLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            alias(libs.plugins.kotlin.multiplatform)
            alias(libs.plugins.compose.multiplatform)
            alias(libs.plugins.compose.multiplatform.compiler)
        }

        configureComposeMultiplatform(
            kotlinExtension = extensions.getByType(),
            composeDependencies = extensions.getByType<ComposeExtension>().dependencies,
            composeCompilerExtension = extensions.getByType(),
            androidExtension = extensions.getByType<LibraryExtension>(),
        )
    }
}
