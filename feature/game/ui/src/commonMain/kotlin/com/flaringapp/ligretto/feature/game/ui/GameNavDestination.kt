package com.flaringapp.ligretto.feature.game.ui

import com.flaringapp.ligretto.core.navigation.LigrettoNavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface GameNavDestination : LigrettoNavKey

@Serializable
internal data class GameStartDestination(
    val restartLastGame: Boolean,
) : GameNavDestination

@Serializable
internal data object GameLapStartDestination : GameNavDestination

@Serializable
internal data object GameLapCardsLeftDestination : GameNavDestination

@Serializable
internal data object GameLapCardsOnTableDestination : GameNavDestination

@Serializable
internal data object GameSettingsDestination : GameNavDestination

@Serializable
internal data object GameScoreDestination : GameNavDestination

@Serializable
internal data object GameCloseDestination : GameNavDestination

@Serializable
internal data object GameEndDestination : GameNavDestination
