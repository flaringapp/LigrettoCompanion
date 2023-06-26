import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jlleitschuh.gradle.ktlint.KtlintExtension

// https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.ktlintGradle)
    alias(libs.plugins.dependencyVersions)
}

//region Ktlint configuration
configure<KtlintExtension> {
    configure(buildDir)
}

subprojects {
    apply(plugin = rootProject.libs.plugins.ktlintGradle.get().pluginId)

    configure<KtlintExtension> {
        configure(buildDir)
    }
}

fun KtlintExtension.configure(buildDir: File) {
    version.set("0.47.1")
    android.set(true)

    @Suppress("DEPRECATION")
    disabledRules.set(
        setOf(
            "trailing-comma-on-call-site",
            "trailing-comma-on-declaration-site",
        )
    )

    filter {
        exclude { it.file.path.contains("${buildDir.name}/generated/") }
    }
}
//endregion

//region Dependency updates
tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isStable(currentVersion) && !isStable(candidate.version)
    }
}

fun isStable(version: String): Boolean {
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    return regex.matches(version)
}
//endregion

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
