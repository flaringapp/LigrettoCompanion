package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.emptyUiList
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState

internal data class GameStartState(
    val players: Players = Players(),
    val endConditions: EndConditions = EndConditions(),
) : UiState {

    data class Players(
        val list: UiList<Player> = emptyUiList(),
        val playersIdCounter: Int = 0,
        val focusedPlayerId: PlayerId? = null,
    ) : UiState

    data class Player(
        val id: PlayerId,
        val name: String,
    )

    sealed class PlayerId {

        data class Existing(
            val value: Long,
        ) : PlayerId()

        data class New(
            val value: Long,
        ) : PlayerId()
    }

    data class EndConditions(
        val isExpandedConditionsCompleted: Boolean = false,
        val isExpandedOptionsCompleted: Boolean = false,
        val score: GameEndConditionScoreLimitState = GameEndConditionScoreLimitState(),
        val time: GameEndConditionTimeLimitState = GameEndConditionTimeLimitState(),
    ) : UiState {

        val isExpandedCompleted: Boolean
            get() = isExpandedConditionsCompleted && isExpandedOptionsCompleted
    }
}
