package com.flaringapp.ligretto.android.ui.feature.game.score

import androidx.compose.runtime.Immutable
import com.flaringapp.ligretto.android.ui.mvi.UiState

data class GameScoreState(
    val scores: List<PlayerScore> = emptyList(),
) : UiState {

    @Immutable
    data class PlayerScore(
        val id: Int,
        val playerName: String,
        val score: Int,
    )
}
