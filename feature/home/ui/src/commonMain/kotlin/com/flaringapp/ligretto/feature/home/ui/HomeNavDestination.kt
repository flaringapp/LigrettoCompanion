package com.flaringapp.ligretto.feature.home.ui

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeNavDestination : NavKey

@Serializable
data object HomeScreenDestination : HomeNavDestination

@Serializable
internal data object GameEndedDestination : HomeNavDestination
