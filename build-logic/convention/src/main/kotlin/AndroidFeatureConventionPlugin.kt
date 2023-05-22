import com.flaringapp.ligretto.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("ligretto.android.library")
            apply("ligretto.android.library.compose")
            apply("ligretto.android.ksp")
            apply("ligretto.android.koin.ksp")
        }

        dependencies {
            add("implementation", project(":core:ui"))
            add("implementation", project(":core:designsystem"))
            add("implementation", project(":core:arch"))
            add("implementation", project(":core:navigation"))

            add("implementation", libs.findLibrary("kotlinx-coroutines-android").get())

            add("implementation", libs.findLibrary("androidx-core-ktx").get())
            add("implementation", libs.findLibrary("androidx-lifecycle-core").get())
            add("implementation", libs.findLibrary("androidx-lifecycle-viewModel").get())

            add("implementation", libs.findLibrary("androidx-lifecycle-compose").get())
        }
    }
}
