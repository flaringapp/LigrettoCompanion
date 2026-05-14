plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.core.database.test"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.core.database)
            api(projects.core.util.databaseTest)
        }
    }
}
