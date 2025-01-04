import com.flaringapp.ligretto.alias
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformDatabaseConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            alias(libs.plugins.sqlDelight)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    api(libs.sqlDelight.coroutines)
                    api(libs.sqlDelight.runtime)
                }
                androidMain.dependencies {
                    implementation(libs.sqlDelight.driver.android)
                }
                nativeMain.dependencies {
                    implementation(libs.sqlDelight.driver.native)
                }
            }
        }
    }
}
