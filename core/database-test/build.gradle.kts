plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.core.database.test"
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":core:database"))
            api(project(":core:util:database-test"))
        }
    }
}
