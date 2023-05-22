package com.flaringapp.ligretto.feature.game.ui.score

import com.flaringapp.ligretto.core.arch.UiIntent

sealed interface GameScoreIntent : UiIntent {

    object LoadData : GameScoreIntent
    data class InitData(
        val scores: List<GameScoreState.PlayerScore>,
        val endConditions: GameScoreState.EndConditions?,
    ) : GameScoreIntent

    object StartNextLap : GameScoreIntent
}
