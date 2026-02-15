package com.flaringapp.ligretto.feature.game.ui.score

import androidx.compose.runtime.Immutable
import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.emptyUiList
import kotlin.time.Clock
import kotlin.time.Instant

internal data class GameScoreState(
    val nextRoundNumber: Int = 0,
    val playerScores: UiList<PlayerScore> = emptyUiList(),
    val endConditions: EndConditions? = null,
) : UiState {

    data class PlayerScore(
        val place: Int,
        val playerName: String,
        val score: Int,
    )

    data class EndConditions(
        val score: Int? = null,
        val time: Time? = null,
    ) {

        @Immutable
        data class Time(
            val timeEnd: Instant,
            val clock: Clock,
        )
    }
}
