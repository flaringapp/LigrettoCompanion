import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.sqlDelight) apply false
    alias(libs.plugins.ktlintGradle)
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
    version.set("1.2.1")
    android.set(true)

    filter {
        exclude { it.file.path.contains("${buildDir.name}/generated/") }
    }
}
//endregion

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
