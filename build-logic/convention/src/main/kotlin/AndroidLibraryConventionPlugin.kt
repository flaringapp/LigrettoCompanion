import com.android.build.gradle.LibraryExtension
import com.flaringapp.ligretto.configureAndroid
import com.flaringapp.ligretto.configureKotlinAndroid
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("android-library").get().get().pluginId)
            apply(libs.findPlugin("kotlin-android").get().get().pluginId)
        }

        extensions.configure<LibraryExtension> {
            configureAndroid(this)
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            configureKotlinAndroid(this)
        }

        dependencies {
            add("implementation", libs.findLibrary("napier").get())

            add("androidTestImplementation", libs.findLibrary("kotlin-test").get())
            add("testImplementation", libs.findLibrary("kotlin-test").get())
        }
    }
}
