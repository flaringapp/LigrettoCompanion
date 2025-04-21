plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":core:designsystem"))

            api(compose.runtime)
            api(compose.ui)
            api(compose.material3)

            implementation(compose.components.resources)
        }
    }
}

compose.resources {
    publicResClass = true
}

android {
    namespace = "com.flaringapp.ligretto.core.ui"
}
