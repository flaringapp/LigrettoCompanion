package com.flaringapp.ligretto.android.ui.feature.game.end

import com.flaringapp.ligretto.core.arch.UiIntent

sealed interface GameEndIntent : UiIntent {

    object LoadData : GameEndIntent
    data class InitData(
        val winners: GameEndState.Winners,
    ) : GameEndIntent

    object Finish : GameEndIntent
}
