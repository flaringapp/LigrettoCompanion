import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.flaringapp.ligretto.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.koin.compiler.gradlePlugin)
    compileOnly(libs.compose.multiplatform.gradlePlugin)
    compileOnly(libs.compose.multiplatform.compiler.gradlePlugin)

    // Enable version catalog accessor generation
    gradle.serviceOf<DependenciesAccessors>().classes.asFiles.forEach {
        compileOnly(files(it.absolutePath))
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.ligretto.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = libs.plugins.ligretto.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.ligretto.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = libs.plugins.ligretto.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("multiplatformFeature") {
            id = libs.plugins.ligretto.multiplatform.feature.get().pluginId
            implementationClass = "MultiplatformFeatureConventionPlugin"
        }
        register("multiplatformLibrary") {
            id = libs.plugins.ligretto.multiplatform.library.asProvider().get().pluginId
            implementationClass = "MultiplatformLibraryConventionPlugin"
        }
        register("multiplatformComposeLibrary") {
            id = libs.plugins.ligretto.multiplatform.library.compose.get().pluginId
            implementationClass = "MultiplatformComposeLibraryConventionPlugin"
        }
        register("multiplatformKoinCompiler") {
            id = libs.plugins.ligretto.multiplatform.koin.compiler.get().pluginId
            implementationClass = "MultiplatformKoinCompilerConventionPlugin"
        }
        register("multiplatformDatabase") {
            id = libs.plugins.ligretto.multiplatform.database.get().pluginId
            implementationClass = "MultiplatformDatabaseConventionPlugin"
        }
    }
}
