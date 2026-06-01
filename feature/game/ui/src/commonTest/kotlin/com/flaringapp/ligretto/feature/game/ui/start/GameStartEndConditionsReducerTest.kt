package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.feature.game.domain.usecase.ValidateGameEndConditionCustomScoreUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.ValidateGameEndConditionCustomTimeUseCase
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsScoreReducer
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsTimeReducer
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GameStartEndConditionsReducerTest {

    private lateinit var reducer: GameStartEndConditionsReducer

    @BeforeTest
    fun setUp() {
        reducer = GameStartEndConditionsReducer(
            scoreReducer = GameEndConditionsScoreReducer(
                validateCustomScoreUseCase = FakeValidateGameEndConditionCustomScoreUseCase(),
            ),
            timeReducer = GameEndConditionsTimeReducer(
                validateCustomTimeUseCase = FakeValidateGameEndConditionCustomTimeUseCase(),
            ),
        )
    }

    @Test
    fun `SubmitStep completes both steps when all conditions are disabled`() {
        val state = GameStartState.EndConditions(
            isExpandedConditionsCompleted = false,
            isExpandedOptionsCompleted = false,
        )

        val result = reducer.reduce(state, GameStartEndConditionsIntent.SubmitStep)

        assertCompletionState(
            state = result,
            expectedIsExpandedConditionsCompleted = true,
            expectedIsExpandedOptionsCompleted = true,
        )
    }

    @Test
    fun `SubmitStep completes only conditions step when any condition is enabled`() {
        val state = GameStartState.EndConditions(
            isExpandedConditionsCompleted = false,
            isExpandedOptionsCompleted = false,
            score = GameEndConditionScoreLimitState(isEnabled = true),
        )

        val result = reducer.reduce(state, GameStartEndConditionsIntent.SubmitStep)

        assertCompletionState(
            state = result,
            expectedIsExpandedConditionsCompleted = true,
            expectedIsExpandedOptionsCompleted = false,
        )
    }

    @Test
    fun `SubmitStep completes options step when conditions step is already completed`() {
        val state = GameStartState.EndConditions(
            isExpandedConditionsCompleted = true,
            isExpandedOptionsCompleted = false,
            score = GameEndConditionScoreLimitState(isEnabled = true),
        )

        val result = reducer.reduce(state, GameStartEndConditionsIntent.SubmitStep)

        assertCompletionState(
            state = result,
            expectedIsExpandedConditionsCompleted = true,
            expectedIsExpandedOptionsCompleted = true,
        )
    }

    @Test
    fun `SubmitStep keeps both steps completed when both are already completed`() {
        val state = GameStartState.EndConditions(
            isExpandedConditionsCompleted = true,
            isExpandedOptionsCompleted = true,
            score = GameEndConditionScoreLimitState(isEnabled = true),
        )

        val result = reducer.reduce(state, GameStartEndConditionsIntent.SubmitStep)

        assertCompletionState(
            state = result,
            expectedIsExpandedConditionsCompleted = true,
            expectedIsExpandedOptionsCompleted = true,
        )
    }

    @Test
    fun `PreviousStep keeps initial state when both steps are incomplete`() {
        val state = GameStartState.EndConditions(
            isExpandedConditionsCompleted = false,
            isExpandedOptionsCompleted = false,
        )

        val result = reducer.reduce(state, GameStartEndConditionsIntent.PreviousStep)

        assertCompletionState(
            state = result,
            expectedIsExpandedConditionsCompleted = false,
            expectedIsExpandedOptionsCompleted = false,
        )
    }

    @Test
    fun `PreviousStep resets conditions step when options step is incomplete and any condition is enabled`() {
        val state = GameStartState.EndConditions(
            isExpandedConditionsCompleted = true,
            isExpandedOptionsCompleted = false,
            score = GameEndConditionScoreLimitState(isEnabled = true),
        )

        val result = reducer.reduce(state, GameStartEndConditionsIntent.PreviousStep)

        assertCompletionState(
            state = result,
            expectedIsExpandedConditionsCompleted = false,
            expectedIsExpandedOptionsCompleted = false,
        )
    }

    @Test
    fun `PreviousStep resets options step when both steps are completed and any condition is enabled`() {
        val state = GameStartState.EndConditions(
            isExpandedConditionsCompleted = true,
            isExpandedOptionsCompleted = true,
            time = GameEndConditionTimeLimitState(isEnabled = true),
        )

        val result = reducer.reduce(state, GameStartEndConditionsIntent.PreviousStep)

        assertCompletionState(
            state = result,
            expectedIsExpandedConditionsCompleted = true,
            expectedIsExpandedOptionsCompleted = false,
        )
    }

    @Test
    fun `PreviousStep resets both steps when both steps are completed and all conditions are disabled`() {
        val state = GameStartState.EndConditions(
            isExpandedConditionsCompleted = true,
            isExpandedOptionsCompleted = true,
        )

        val result = reducer.reduce(state, GameStartEndConditionsIntent.PreviousStep)

        assertCompletionState(
            state = result,
            expectedIsExpandedConditionsCompleted = false,
            expectedIsExpandedOptionsCompleted = false,
        )
    }

    private fun assertCompletionState(
        state: GameStartState.EndConditions,
        expectedIsExpandedConditionsCompleted: Boolean,
        expectedIsExpandedOptionsCompleted: Boolean,
    ) {
        assertEquals(expectedIsExpandedConditionsCompleted, state.isExpandedConditionsCompleted)
        assertEquals(expectedIsExpandedOptionsCompleted, state.isExpandedOptionsCompleted)
    }

    private class FakeValidateGameEndConditionCustomScoreUseCase :
        ValidateGameEndConditionCustomScoreUseCase {

        override fun invoke(score: Int) = null
    }

    private class FakeValidateGameEndConditionCustomTimeUseCase :
        ValidateGameEndConditionCustomTimeUseCase {

        override fun invoke(minutes: Int) = null
    }
}
