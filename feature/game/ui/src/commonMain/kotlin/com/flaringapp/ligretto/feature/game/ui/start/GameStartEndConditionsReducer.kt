package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.core.arch.Reducer
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions

internal object GameStartEndConditionsReducer :
    Reducer<EndConditions, GameStartEndConditionsIntent> {

    override fun reduce(
        state: EndConditions,
        intent: GameStartEndConditionsIntent,
    ): EndConditions = when (intent) {
        is GameStartEndConditionsIntent.SubmitStep -> {
            state.submitStep()
        }

        is GameStartScoreEndConditionIntent.SetEnabled -> {
            state.copy(
                score = state.score.copy(
                    isEnabled = intent.isEnabled,
                ),
            )
        }

        is GameStartScoreEndConditionIntent.ValueChange -> {
            state.copy(
                score = state.score.copy(
                    selectedScore = intent.score,
                ),
            )
        }

        is GameStartScoreEndConditionIntent.SelectCustomValue -> {
            // TODO complete
            state
        }

        is GameStartScoreEndConditionIntent.CustomValueSelected -> {
            state.copy(
                score = state.score.copy(
                    selectedScore = intent.score,
                    lastCustomScore = intent.score,
                ),
            )
        }

        is GameStartTimeEndConditionIntent.SetEnabled -> {
            state.copy(
                time = state.time.copy(
                    isEnabled = intent.isEnabled,
                ),
            )
        }

        is GameStartTimeEndConditionIntent.ValueChange -> {
            state.copy(
                time = state.time.copy(
                    selectedMinutes = intent.minutes,
                ),
            )
        }

        is GameStartTimeEndConditionIntent.SelectCustomValue -> {
            // TODO complete
            state
        }

        is GameStartTimeEndConditionIntent.CustomValueSelected -> {
            state.copy(
                time = state.time.copy(
                    selectedMinutes = intent.minutes,
                    lastCustomMinutes = intent.minutes,
                ),
            )
        }
    }

    private fun EndConditions.submitStep(): EndConditions {
        if (isExpandedConditionsCompleted) {
            return copy(isExpandedOptionsCompleted = true)
        }

        return copy(
            isExpandedConditionsCompleted = true,
            isExpandedOptionsCompleted = !score.isEnabled && !time.isEnabled,
        )
    }
}
