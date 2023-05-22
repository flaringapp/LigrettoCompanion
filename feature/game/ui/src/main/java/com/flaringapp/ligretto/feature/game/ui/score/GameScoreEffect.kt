package com.flaringapp.ligretto.feature.game.ui.score

import com.flaringapp.ligretto.core.arch.UiEffect

sealed interface GameScoreEffect : UiEffect {

    object OpenNextLap : GameScoreEffect

    object EndGame : GameScoreEffect
}
