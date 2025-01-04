import com.android.build.gradle.LibraryExtension
import com.flaringapp.ligretto.alias
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
            alias(libs.plugins.android.library)
            alias(libs.plugins.kotlin.android)
        }

        extensions.configure<LibraryExtension> {
            configureAndroid(this)
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            configureKotlinAndroid(this)
        }

        dependencies {
            add("implementation", libs.napier)

            add("androidTestImplementation", libs.kotlin.test)
            add("testImplementation", libs.kotlin.test)
        }
    }
}
