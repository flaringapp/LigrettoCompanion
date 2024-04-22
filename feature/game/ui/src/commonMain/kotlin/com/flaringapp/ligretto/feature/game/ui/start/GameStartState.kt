package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.emptyUiList

internal data class GameStartState(
    val players: Players = Players(),
    val endConditions: EndConditions = EndConditions(),
) : UiState {

    data class Players(
        val list: UiList<Player> = emptyUiList(),
        val playersIdCounter: Int = 0,
        val focusedPlayerId: Int? = null,
    )

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
