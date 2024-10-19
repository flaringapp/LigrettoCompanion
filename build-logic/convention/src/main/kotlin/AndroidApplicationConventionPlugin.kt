import com.android.build.api.dsl.ApplicationExtension
import com.flaringapp.ligretto.configureAndroid
import com.flaringapp.ligretto.configureKotlinAndroid
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("android-application").get().get().pluginId)
            apply(libs.findPlugin("kotlin-android").get().get().pluginId)
        }

        extensions.configure<ApplicationExtension> {
            configureAndroid(this)
            defaultConfig.targetSdk = 35
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
