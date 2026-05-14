plugins {
    alias(libs.plugins.ligretto.multiplatform.library)
}

kotlin {
    android {
        namespace = "com.flaringapp.ligretto.feature.game"
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":feature:game:ui"))
            api(project(":feature:game:data"))
            api(project(":feature:game:domain"))
            api(project(":feature:game:domain-contracts"))
            api(project(":feature:game:model"))
        }
    }
}
