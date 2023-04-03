package com.flaringapp.ligretto.android.ui.feature.game.end

import com.flaringapp.ligretto.android.ui.mvi.UiIntent

sealed interface GameEndIntent : UiIntent {

    data class InitData(
        val winners: GameEndState.Winners,
    ) : GameEndIntent

    object Finish : GameEndIntent
}
