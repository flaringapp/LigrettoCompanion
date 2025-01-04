package com.flaringapp.ligretto.feature.game.ui.start

import kotlinx.serialization.Serializable

@Serializable
internal data class GameStartDestination(
    val restartLastGame: Boolean,
)
