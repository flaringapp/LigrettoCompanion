plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.core.util.common"
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.coroutines.core)
        }
    }
}
