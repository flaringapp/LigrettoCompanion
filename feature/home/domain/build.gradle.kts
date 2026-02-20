plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.koin.ksp")
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.feature.home.domain"
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":feature:game:model"))
            implementation(project(":feature:game:domain-contracts"))
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}
