import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.multiplatform.compiler) apply false
    alias(libs.plugins.sqlDelight) apply false
    alias(libs.plugins.ktlintGradle)
}

//region Ktlint configuration
configure<KtlintExtension> {
    configure(layout.buildDirectory)
}

subprojects {
    apply(plugin = rootProject.libs.plugins.ktlintGradle.get().pluginId)

    configure<KtlintExtension> {
        configure(layout.buildDirectory)
    }
}

fun KtlintExtension.configure(buildDir: DirectoryProperty) {
    version.set("1.4.0")
    android.set(true)

    val buildDirFile = buildDir.get().asFile
    filter {
        exclude { it.file.path.contains("${buildDirFile.name}/generated/") }
    }
}
//endregion

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
