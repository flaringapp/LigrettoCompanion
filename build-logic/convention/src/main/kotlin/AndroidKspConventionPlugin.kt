import com.android.build.api.variant.AndroidComponentsExtension
import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

class AndroidKspConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlin-android").get().get().pluginId)
            apply(libs.findPlugin("ksp").get().get().pluginId)
        }

        androidComponents {
            onVariants { variant ->
                kotlinExtension.sourceSets.findByName(variant.name)?.kotlin?.srcDirs(
                    file("$buildDir/generated/ksp/${variant.name}/kotlin")
                )
            }
        }
    }
}

internal fun Project.androidComponents(
    configure: AndroidComponentsExtension<*, *, *>.() -> Unit
) {
    (this as ExtensionAware).extensions.configure("androidComponents", configure)
}
