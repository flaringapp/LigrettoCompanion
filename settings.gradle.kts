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
include(":core:util:database-test")
include(":core:designsystem-mp")
include(":core:ui-mp")
include(":core:navigation-mp")
include(":core:arch-mp")
include(":core:arch-koin")
include(":core:database")
include(":core:database-test")
include(":core:settings")
include(":core:settings-test")
include(":feature:home:ui")
include(":feature:home:domain")
include(":feature:home:di")
include(":feature:game:ui")
include(":feature:game:model")
include(":feature:game:domain")
include(":feature:game:domain-contracts")
include(":feature:game:data")
include(":feature:game:di")
