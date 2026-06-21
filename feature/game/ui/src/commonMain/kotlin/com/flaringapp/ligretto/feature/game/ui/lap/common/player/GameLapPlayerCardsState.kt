package com.flaringapp.ligretto.feature.game.ui.lap.common.player

import com.flaringapp.ligretto.core.ui.components.UiPlayerAvatarType

internal data class GameLapPlayerCardsState(
    val playerId: Long,
    val playerName: String,
    val playerAvatar: UiPlayerAvatarType?,
    val totalScore: Int,
    val cardsCount: Int,
)
