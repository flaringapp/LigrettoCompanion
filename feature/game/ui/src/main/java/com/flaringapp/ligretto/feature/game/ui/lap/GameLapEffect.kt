package com.flaringapp.ligretto.feature.game.ui.lap

import com.flaringapp.ligretto.core.arch.UiEffect

internal sealed interface GameLapEffect : UiEffect {

    object OpenScores : GameLapEffect

    object EndGame : GameLapEffect
}
