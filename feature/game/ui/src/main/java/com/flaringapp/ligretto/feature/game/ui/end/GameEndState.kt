package com.flaringapp.ligretto.feature.game.ui.end

import com.flaringapp.ligretto.core.arch.UiState

data class GameEndState(
    val winners: Winners? = null,
) : UiState {

    data class Winners(
        val firstPlace: PlayerResult,
        val secondPlace: PlayerResult?,
        val thirdPlace: PlayerResult?,
    )

    data class PlayerResult(
        val name: String,
        val score: Int,
    )
}
