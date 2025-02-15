package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.core.arch.UiIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeIntent

internal sealed interface GameStartIntent : UiIntent {

    object FetchDataFromLastGame : GameStartIntent

    object StartGame : GameStartIntent
}

internal sealed interface GameStartPlayersIntent : GameStartIntent {

    object AddNew : GameStartPlayersIntent

    data class ChangeName(
        val id: GameStartState.PlayerId,
        val name: String,
    ) : GameStartPlayersIntent

    data class FocusChanged(
        val id: GameStartState.PlayerId,
        val isFocused: Boolean,
    ) : GameStartPlayersIntent

    data class Remove(
        val id: GameStartState.PlayerId,
    ) : GameStartPlayersIntent
}

internal sealed interface GameStartEndConditionsIntent : GameStartIntent {

    data object SubmitStep : GameStartEndConditionsIntent

    data class Score(
        val intent: GameEndConditionScoreIntent,
    ) : GameStartEndConditionsIntent

    data class Time(
        val intent: GameEndConditionTimeIntent,
    ) : GameStartEndConditionsIntent
}
