package com.flaringapp.ligretto.feature.game.ui.settings

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsScoreReducer
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsTimeReducer
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class GameSettingsViewModel :
    MviViewModel<GameSettingsState, GameSettingsIntent, GameSettingsEffect>(
        GameSettingsState(),
    ) {

    override fun reduce(
        state: GameSettingsState,
        intent: GameSettingsIntent,
    ): GameSettingsState = when (intent) {
        is GameSettingsIntent.EndConditions.Score -> {
            state.copy(
                score = GameEndConditionsScoreReducer.reduce(
                    state = state.score,
                    intent = intent.intent,
                ),
            )
        }

        is GameSettingsIntent.EndConditions.Time -> {
            state.copy(
                time = GameEndConditionsTimeReducer.reduce(
                    state = state.time,
                    intent = intent.intent,
                ),
            )
        }

        GameSettingsIntent.Save -> save()
    }

    private fun save() = state.also {
        // TODO

        setEffect { GameSettingsEffect.Close }
    }
}
