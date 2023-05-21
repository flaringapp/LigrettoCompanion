package com.flaringapp.ligretto.android.ui.feature.game.lap

import com.flaringapp.ligretto.core.arch.UiEffect

sealed interface GameLapEffect : UiEffect {

    object OpenScores : GameLapEffect

    object EndGame : GameLapEffect
}
