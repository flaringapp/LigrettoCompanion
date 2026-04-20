import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.multiplatform.compiler) apply false
    alias(libs.plugins.sqlDelight) apply false
    alias(libs.plugins.ktlintGradle)
}

//region Ktlint configuration
configure<KtlintExtension> {
    configure()
}

subprojects {
    apply(plugin = rootProject.libs.plugins.ktlintGradle.get().pluginId)

    configure<KtlintExtension> {
        configure()
    }
}

fun KtlintExtension.configure() {
    version.set("1.8.0")
    android.set(true)
}
//endregion

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
