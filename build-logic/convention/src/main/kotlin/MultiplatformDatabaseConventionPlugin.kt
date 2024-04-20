import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformDatabaseConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("sqlDelight").get().get().pluginId)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    api(libs.findLibrary("sqlDelight-coroutines").get())
                    api(libs.findLibrary("sqlDelight-runtime").get())
                }
                androidMain.dependencies {
                    implementation(libs.findLibrary("sqlDelight-driver-android").get())
                }
                iosMain.dependencies {
                    implementation(libs.findLibrary("sqlDelight-driver-ios").get())
                }
            }
        }
    }
}
