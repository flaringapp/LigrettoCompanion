import com.android.build.api.dsl.ApplicationExtension
import com.flaringapp.ligretto.alias
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
            alias(libs.plugins.android.application)
            alias(libs.plugins.kotlin.android)
        }

        extensions.configure<ApplicationExtension> {
            configureAndroid(this)
            defaultConfig.targetSdk = 35
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
