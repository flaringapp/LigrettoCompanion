package com.flaringapp.ligretto.android.ui.feature.game.score

import com.flaringapp.ligretto.android.ui.mvi.UiIntent

sealed interface GameScoreIntent : UiIntent {

    object LoadData : GameScoreIntent
    data class InitData(val scores: List<GameScoreState.PlayerScore>) : GameScoreIntent

    object StartNextLap : GameScoreIntent
}
