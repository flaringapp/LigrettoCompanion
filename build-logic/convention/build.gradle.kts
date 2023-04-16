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
