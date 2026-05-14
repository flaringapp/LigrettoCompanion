plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
    alias(libs.plugins.ligretto.multiplatform.database)
    alias(libs.plugins.ligretto.multiplatform.koin.compiler)
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
            implementation(projects.core.util.databaseTest)
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
