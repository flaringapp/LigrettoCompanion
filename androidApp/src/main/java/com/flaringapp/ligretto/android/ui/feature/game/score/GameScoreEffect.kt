package com.flaringapp.ligretto.android.ui.feature.game.score

import com.flaringapp.ligretto.android.ui.mvi.UiEffect

sealed interface GameScoreEffect : UiEffect {

    object OpenNextLap : GameScoreEffect
}
