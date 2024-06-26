import com.android.build.gradle.LibraryExtension
import com.flaringapp.ligretto.configureKotlinAndroid
import com.flaringapp.ligretto.configureKotlinMultiplatform
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlin-multiplatform").get().get().pluginId)
            apply(libs.findPlugin("android-library").get().get().pluginId)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureKotlinMultiplatform(this)
        }

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
        }
    }
}
