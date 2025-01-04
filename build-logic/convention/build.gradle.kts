import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.flaringapp.ligretto.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
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
            id = "ligretto.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "ligretto.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "ligretto.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "ligretto.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("multiplatformFeature") {
            id = "ligretto.multiplatform.feature"
            implementationClass = "MultiplatformFeatureConventionPlugin"
        }
        register("multiplatformLibrary") {
            id = "ligretto.multiplatform.library"
            implementationClass = "MultiplatformLibraryConventionPlugin"
        }
        register("multiplatformComposeLibrary") {
            id = "ligretto.multiplatform.library.compose"
            implementationClass = "MultiplatformComposeLibraryConventionPlugin"
        }
        register("multiplatformKoinKsp") {
            id = "ligretto.multiplatform.koin.ksp"
            implementationClass = "MultiplatformKoinKspConventionPlugin"
        }
        register("multiplatformDatabase") {
            id = "ligretto.multiplatform.database"
            implementationClass = "MultiplatformDatabaseConventionPlugin"
        }
    }
}
