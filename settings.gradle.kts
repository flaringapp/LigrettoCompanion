@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Ligretto_Companion"
include(":androidApp")
include(":core:designsystem")
include(":core:ui")
include(":core:arch")
include(":feature:game:model")
include(":feature:game:domain")
include(":feature:game:domain-contracts")
include(":feature:game:data")
