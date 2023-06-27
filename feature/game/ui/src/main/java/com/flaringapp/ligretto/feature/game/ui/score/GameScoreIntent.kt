package com.flaringapp.ligretto.feature.game.ui.score

import com.flaringapp.ligretto.core.arch.UiIntent
import com.flaringapp.ligretto.core.ui.ext.UiList

internal sealed interface GameScoreIntent : UiIntent {

    object LoadData : GameScoreIntent
    data class InitData(
        val scores: UiList<GameScoreState.PlayerScore>,
        val endConditions: GameScoreState.EndConditions?,
    ) : GameScoreIntent

    object StartNextLap : GameScoreIntent
}
