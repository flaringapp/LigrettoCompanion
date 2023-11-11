plugins {
    id("ligretto.multiplatform.library")
    id("ligretto.multiplatform.koin.ksp")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":feature:game:model"))
            implementation(project(":feature:game:domain-contracts"))
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "com.flaringapp.ligretto.feature.home.domain"
}
