package com.flaringapp.ligretto.feature.home.ui

import com.flaringapp.ligretto.core.navigation.LigrettoNavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeNavDestination : LigrettoNavKey

@Serializable
data object HomeScreenDestination : HomeNavDestination

@Serializable
internal data object GameEndedDestination : HomeNavDestination
