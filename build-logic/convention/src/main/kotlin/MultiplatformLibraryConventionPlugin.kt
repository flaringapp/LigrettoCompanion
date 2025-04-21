import com.android.build.gradle.LibraryExtension
import com.flaringapp.ligretto.alias
import com.flaringapp.ligretto.configureAndroid
import com.flaringapp.ligretto.configureKotlinMultiplatform
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            alias(libs.plugins.kotlin.multiplatform)
            alias(libs.plugins.android.library)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureKotlinMultiplatform(this)
        }

        extensions.configure<LibraryExtension> {
            configureAndroid(this)
        }
    }
}
