import com.flaringapp.ligretto.libs
import com.flaringapp.ligretto.sourceSets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused", "UNUSED_VARIABLE")
class MultiplatformDatabaseConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("sqlDelight").get().get().pluginId)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets {
                val commonMain by getting {
                    dependencies {
                        api(libs.findLibrary("sqlDelight-coroutines").get())
                        api(libs.findLibrary("sqlDelight-runtime").get())
                    }
                }
                val androidMain by getting {
                    dependencies {
                        implementation(libs.findLibrary("sqlDelight-driver-android").get())
                    }
                }
                val iosMain by getting {
                    dependencies {
                        implementation(libs.findLibrary("sqlDelight-driver-ios").get())
                    }
                }
            }
        }
    }
}
