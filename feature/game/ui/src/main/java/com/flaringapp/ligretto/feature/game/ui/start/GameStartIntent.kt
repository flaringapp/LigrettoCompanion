package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.core.arch.UiIntent

internal sealed interface GameStartIntent : UiIntent {

    object FetchDataFromLastGame : GameStartIntent

    object StartGame : GameStartIntent
}

internal sealed interface GameStartPlayersIntent : GameStartIntent {

    object AddNew : GameStartPlayersIntent

    data class ChangeName(
        val id: Int,
        val name: String,
    ) : GameStartPlayersIntent

    data class FocusChanged(
        val id: Int,
        val isFocused: Boolean,
    ) : GameStartPlayersIntent

    data class Remove(val id: Int) : GameStartPlayersIntent
}

internal sealed interface GameStartEndConditionsIntent : GameStartIntent

internal sealed interface GameStartScoreEndConditionIntent : GameStartEndConditionsIntent {

    data class SetEnabled(val isEnabled: Boolean) : GameStartScoreEndConditionIntent

    data class ValueChange(val value: String) : GameStartScoreEndConditionIntent
}

internal sealed interface GameStartTimeEndConditionIntent : GameStartEndConditionsIntent {

    data class SetEnabled(val isEnabled: Boolean) : GameStartTimeEndConditionIntent

    data class HourChange(val value: String) : GameStartTimeEndConditionIntent

    data class MinuteChange(val value: String) : GameStartTimeEndConditionIntent
}
