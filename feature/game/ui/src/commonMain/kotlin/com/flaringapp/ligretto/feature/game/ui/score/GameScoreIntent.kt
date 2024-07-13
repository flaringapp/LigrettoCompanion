package com.flaringapp.ligretto.feature.game.ui.score

import com.flaringapp.ligretto.core.arch.UiIntent

internal sealed interface GameScoreIntent : UiIntent {

    object LoadData : GameScoreIntent
    data class InitData(
        val state: GameScoreState,
    ) : GameScoreIntent

    object StartNextLap : GameScoreIntent
}
