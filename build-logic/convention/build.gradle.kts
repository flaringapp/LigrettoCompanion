plugins {
    `kotlin-dsl`
}

group = "com.flaringapp.ligretto.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
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
        register("androidKsp") {
            id = "ligretto.android.ksp"
            implementationClass = "AndroidKspConventionPlugin"
        }
        register("androidKoinKsp") {
            id = "ligretto.android.koin.ksp"
            implementationClass = "AndroidKoinKspConventionPlugin"
        }
        register("multiplatformLibrary") {
            id = "ligretto.multiplatform.library"
            implementationClass = "MultiplatformLibraryConventionPlugin"
        }
        register("multiplatformKoinKsp") {
            id = "ligretto.multiplatform.koin.ksp"
            implementationClass = "MultiplatformKoinKspConventionPlugin"
        }
    }
}