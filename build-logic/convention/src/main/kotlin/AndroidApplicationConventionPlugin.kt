import com.android.build.api.dsl.ApplicationExtension
import com.flaringapp.ligretto.configureKotlinAndroid
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("android-application").get().get().pluginId)
            apply(libs.findPlugin("kotlin-android").get().get().pluginId)
        }

        extensions.configure<ApplicationExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = 33
        }
    }
}
