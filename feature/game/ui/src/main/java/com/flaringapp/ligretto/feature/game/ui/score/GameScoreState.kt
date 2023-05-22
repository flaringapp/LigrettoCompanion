package com.flaringapp.ligretto.feature.game.ui.score

import androidx.compose.runtime.Immutable
import com.flaringapp.ligretto.core.arch.UiState
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

internal data class GameScoreState(
    val scores: List<PlayerScore> = emptyList(),
    val endConditions: EndConditions? = null,
) : UiState {

    @Immutable
    data class PlayerScore(
        val id: Int,
        val playerName: String,
        val score: Int,
    )

    data class EndConditions(
        val score: Int? = null,
        val time: Time? = null,
    ) {

        data class Time(
            val timeEnd: Instant,
            val clock: Clock,
        )
    }
}
