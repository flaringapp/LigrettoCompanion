plugins {
    id("ligretto.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":core:database"))
            api(project(":core:util:database-test"))
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.core.database.test"
}
