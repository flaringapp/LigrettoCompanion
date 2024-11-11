package com.flaringapp.ligretto.feature.game.ui.end

import com.flaringapp.ligretto.core.arch.UiIntent
import com.flaringapp.ligretto.core.ui.ext.UiList

internal sealed interface GameEndIntent : UiIntent {

    object LoadData : GameEndIntent
    data class InitData(
        val scoreboard: UiList<GameEndState.PlayerResult>,
    ) : GameEndIntent

    object Finish : GameEndIntent
}
