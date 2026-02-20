plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.database")
    id("ligretto.multiplatform.koin.ksp")
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.core.database"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(project(":core:util:database-test"))
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.flaringapp.ligretto.core.database")
            generateAsync.set(true)
        }
    }
}
