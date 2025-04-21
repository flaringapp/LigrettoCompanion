package com.flaringapp.ligretto.feature.game.ui.lap.start

import com.flaringapp.ligretto.core.arch.UiIntent

internal sealed interface GameLapStartIntent : UiIntent {

    data object Init : GameLapStartIntent

    data class UpdateData(
        val state: GameLapStartState,
    ) : GameLapStartIntent

    data object StartLap : GameLapStartIntent
}
