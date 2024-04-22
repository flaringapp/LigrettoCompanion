package com.flaringapp.ligretto.feature.game.ui.end

import com.flaringapp.ligretto.core.arch.UiIntent

internal sealed interface GameEndIntent : UiIntent {

    object LoadData : GameEndIntent
    data class InitData(
        val winners: GameEndState.Winners,
    ) : GameEndIntent

    object Finish : GameEndIntent
}
