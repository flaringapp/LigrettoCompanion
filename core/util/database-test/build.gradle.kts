plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.coroutines.core)
            api(libs.sqlDelight.runtime)
        }
        androidMain.dependencies {
            implementation(libs.sqlDelight.driver.sqlite)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.util.database.test"
}
