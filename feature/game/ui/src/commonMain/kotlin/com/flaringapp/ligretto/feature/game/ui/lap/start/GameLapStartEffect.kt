package com.flaringapp.ligretto.feature.game.ui.lap.start

import com.flaringapp.ligretto.core.arch.UiEffect

internal sealed interface GameLapStartEffect : UiEffect {

    data object OpenLap : GameLapStartEffect
}
