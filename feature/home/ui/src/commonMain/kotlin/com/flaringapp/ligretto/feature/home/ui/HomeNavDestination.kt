package com.flaringapp.ligretto.feature.home.ui

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.PolymorphicModuleBuilder

@OptIn(ExperimentalSerializationApi::class)
fun PolymorphicModuleBuilder<NavKey>.homeNavDestinations() {
    subclassesOfSealed<HomeNavDestination>()
}

@Serializable
internal sealed interface HomeNavDestination : NavKey

@Serializable
data object HomeScreenDestination : HomeNavDestination

@Serializable
internal data object GameEndedDestination : HomeNavDestination
