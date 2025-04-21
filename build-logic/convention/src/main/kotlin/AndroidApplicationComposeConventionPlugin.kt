import com.flaringapp.ligretto.alias
import com.flaringapp.ligretto.configureAndroidCompose
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            alias(libs.plugins.android.application)
            alias(libs.plugins.compose.multiplatform)
            alias(libs.plugins.compose.multiplatform.compiler)
        }

        extensions.configure<ComposeCompilerGradlePluginExtension> {
            configureAndroidCompose(this)
        }
    }
}
