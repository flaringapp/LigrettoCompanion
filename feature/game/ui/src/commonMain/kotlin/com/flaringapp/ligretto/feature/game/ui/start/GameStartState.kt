package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.emptyUiList
import com.flaringapp.ligretto.core.ui.ext.uiListOf

internal data class GameStartState(
    val players: Players = Players(),
    val endConditions: EndConditions = EndConditions(),
) : UiState {

    data class Players(
        val list: UiList<Player> = emptyUiList(),
        val playersIdCounter: Int = 0,
        val focusedPlayerId: Int? = null,
    ) : UiState

    data class Player(
        val id: Int,
        val name: String,
    )

    data class EndConditions(
        val isExpandedConditionsCompleted: Boolean = false,
        val isExpandedOptionsCompleted: Boolean = false,
        val score: ScoreLimit = ScoreLimit(),
        val time: TimeLimit = TimeLimit(),
    ) : UiState {

        val isExpandedCompleted: Boolean
            get() = isExpandedConditionsCompleted && isExpandedOptionsCompleted

        data class ScoreLimit(
            val staticOptions: UiList<Int> = uiListOf(50, 100, 150),
            val isEnabled: Boolean = false,
            val selectedScore: Int = staticOptions.first(),
            val lastCustomScore: Int? = null,
        )

        data class TimeLimit(
            val staticOptions: UiList<Int> = uiListOf(15, 30, 60),
            val isEnabled: Boolean = false,
            val selectedMinutes: Int = staticOptions.first(),
            val lastCustomMinutes: Int? = null,
        )
    }
}
