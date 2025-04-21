package com.flaringapp.ligretto.feature.game.ui.end

import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.core.ui.ext.UiList

internal data class GameEndState(
    val scoreboard: UiList<PlayerResult>? = null,
) : UiState {

    data class PlayerResult(
        val name: String,
        val score: Int,
    )
}
