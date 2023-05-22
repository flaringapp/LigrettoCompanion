package com.flaringapp.ligretto.feature.game.ui.start

import androidx.compose.runtime.Immutable
import com.flaringapp.ligretto.core.arch.UiState

internal data class GameStartState(
    val players: Players = Players(),
    val endConditions: EndConditions = EndConditions(),
) : UiState {

    data class Players(
        val list: List<Player> = emptyList(),
        val playersIdCounter: Int = 0,
        val focusedPlayerId: Int? = null,
    )

    @Immutable
    data class Player(
        val id: Int,
        val name: String,
    )

    data class EndConditions(
        val score: ScoreLimit = ScoreLimit(),
        val time: TimeLimit = TimeLimit(),
    ) {

        data class ScoreLimit(
            val isEnabled: Boolean = false,
            val value: String = "100",
        )

        data class TimeLimit(
            val isEnabled: Boolean = false,
            val hours: String = "0",
            val minutes: String = "30",
        )
    }
}
