plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
    alias(libs.plugins.ligretto.multiplatform.koin.compiler)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.feature.game.data"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:database"))
            implementation(project(":core:settings"))
            implementation(project(":feature:game:model"))
            implementation(project(":feature:game:domain-contracts"))
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(project(":core:database-test"))
            implementation(project(":core:settings-test"))
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}
