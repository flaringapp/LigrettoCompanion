package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.core.arch.Reducer
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsScoreReducer
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsTimeReducer
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions
import org.koin.core.annotation.Factory

@Factory
internal class GameStartEndConditionsReducer(
    private val scoreReducer: GameEndConditionsScoreReducer,
    private val timeReducer: GameEndConditionsTimeReducer,
) : Reducer<EndConditions, GameStartEndConditionsIntent> {

    override fun reduce(
        state: EndConditions,
        intent: GameStartEndConditionsIntent,
    ): EndConditions = when (intent) {
        is GameStartEndConditionsIntent.SubmitStep -> {
            state.submitStep()
        }

        is GameStartEndConditionsIntent.Score -> {
            state.copy(
                score = scoreReducer.reduce(
                    state = state.score,
                    intent = intent.intent,
                ),
            )
        }

        is GameStartEndConditionsIntent.Time -> {
            state.copy(
                time = timeReducer.reduce(
                    state = state.time,
                    intent = intent.intent,
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
