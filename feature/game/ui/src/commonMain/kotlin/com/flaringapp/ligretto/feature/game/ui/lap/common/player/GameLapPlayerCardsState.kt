package com.flaringapp.ligretto.feature.game.ui.lap.common.player

internal data class GameLapPlayerCardsState(
    val playerId: Long,
    val playerName: String,
    val totalScore: Int,
    val cardsCount: Int,
)
