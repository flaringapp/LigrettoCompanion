package com.flaringapp.ligretto.android.ui.feature.game.lap

import com.flaringapp.ligretto.android.ui.mvi.UiEffect

sealed interface GameLapEffect : UiEffect {

    object OpenScores : GameLapEffect
}
