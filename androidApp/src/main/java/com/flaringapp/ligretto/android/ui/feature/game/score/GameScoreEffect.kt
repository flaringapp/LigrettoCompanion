package com.flaringapp.ligretto.android.ui.feature.game.score

import com.flaringapp.ligretto.core.arch.UiEffect

sealed interface GameScoreEffect : UiEffect {

    object OpenNextLap : GameScoreEffect

    object EndGame : GameScoreEffect
}
