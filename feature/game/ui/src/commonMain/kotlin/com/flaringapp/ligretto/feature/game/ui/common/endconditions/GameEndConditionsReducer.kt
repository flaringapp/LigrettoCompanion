package com.flaringapp.ligretto.feature.game.ui.common.endconditions

import com.flaringapp.ligretto.core.arch.Reducer

internal object GameEndConditionsScoreReducer :
    Reducer<GameEndConditionScoreLimitState, GameEndConditionScoreIntent> {

    override fun reduce(
        state: GameEndConditionScoreLimitState,
        intent: GameEndConditionScoreIntent,
    ): GameEndConditionScoreLimitState = when (intent) {
        is GameEndConditionScoreIntent.SetEnabled -> {
            state.copy(
                isEnabled = intent.isEnabled,
            )
        }

        is GameEndConditionScoreIntent.ValueChange -> {
            state.copy(
                selectedScore = intent.score,
            )
        }

        is GameEndConditionScoreIntent.SelectCustomValue -> {
            // TODO complete
            state
        }

        is GameEndConditionScoreIntent.CustomValueSelected -> {
            state.copy(
                selectedScore = intent.score,
                lastCustomScore = intent.score,
            )
        }
    }
}

internal object GameEndConditionsTimeReducer :
    Reducer<GameEndConditionTimeLimitState, GameEndConditionTimeIntent> {

    override fun reduce(
        state: GameEndConditionTimeLimitState,
        intent: GameEndConditionTimeIntent,
    ): GameEndConditionTimeLimitState = when (intent) {
        is GameEndConditionTimeIntent.SetEnabled -> {
            state.copy(
                isEnabled = intent.isEnabled,
            )
        }

        is GameEndConditionTimeIntent.ValueChange -> {
            state.copy(
                selectedMinutes = intent.minutes,
            )
        }

        is GameEndConditionTimeIntent.SelectCustomValue -> {
            // TODO complete
            state
        }

        is GameEndConditionTimeIntent.CustomValueSelected -> {
            state.copy(
                selectedMinutes = intent.minutes,
                lastCustomMinutes = intent.minutes,
            )
        }
    }
}
