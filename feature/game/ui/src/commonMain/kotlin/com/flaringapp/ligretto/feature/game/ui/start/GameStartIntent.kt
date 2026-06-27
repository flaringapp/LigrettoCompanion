package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.core.arch.UiIntent
import com.flaringapp.ligretto.core.ui.components.player.image.UiPlayerAvatarType
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeIntent

internal sealed interface GameStartIntent : UiIntent {

    object FetchDataFromLastGame : GameStartIntent

    object StartGame : GameStartIntent
}

internal sealed interface GameStartPlayersIntent : GameStartIntent {

    data class AddNew(
        val avatar: UiPlayerAvatarType = UiPlayerAvatarType.entries.random(),
    ) : GameStartPlayersIntent

    data class ChangeName(
        val id: GameStartState.PlayerId,
        val name: String,
    ) : GameStartPlayersIntent

    data class ChangeAvatar(
        val id: GameStartState.PlayerId,
        val avatar: UiPlayerAvatarType?,
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

    data object PreviousStep : GameStartEndConditionsIntent

    data class Score(
        val intent: GameEndConditionScoreIntent,
    ) : GameStartEndConditionsIntent

    data class Time(
        val intent: GameEndConditionTimeIntent,
    ) : GameStartEndConditionsIntent
}
