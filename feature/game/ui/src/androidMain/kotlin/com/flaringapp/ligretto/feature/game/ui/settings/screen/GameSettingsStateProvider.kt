package com.flaringapp.ligretto.feature.game.ui.settings.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState
import com.flaringapp.ligretto.feature.game.ui.settings.GameSettingsState

internal class GameSettingsStateProvider : PreviewParameterProvider<GameSettingsState> {

    companion object {

        fun unset() = GameSettingsState(
            score = GameEndConditionScoreLimitState(),
            time = GameEndConditionTimeLimitState(),
        )

        fun scoreSet() = unset().copy(
            score = GameEndConditionScoreLimitState(isEnabled = true),
        )

        fun allSet() = GameSettingsState(
            score = GameEndConditionScoreLimitState(isEnabled = true),
            time = GameEndConditionTimeLimitState(isEnabled = true),
        )
    }

    override val values: Sequence<GameSettingsState> = sequenceOf(
        unset(),
        scoreSet(),
        allSet(),
    )
}
