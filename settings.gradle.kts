@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

// Used by Gradle Daemon JVM criteria to download missing JDKs
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Ligretto_Companion"
include(":androidApp")
include(":commonApp")
include(":core:util:common")
include(":core:util:database-test")
include(":core:di")
include(":core:designsystem")
include(":core:ui")
include(":core:arch")
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
