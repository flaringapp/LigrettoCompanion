package com.flaringapp.ligretto.feature.game.ui.start

import kotlin.test.Test
import kotlin.test.assertEquals

class GameStartStateTest {

    @Test
    fun `EndConditions derived state is correct when both steps are incomplete`() {
        val state = GameStartState.EndConditions(
            isExpandedConditionsCompleted = false,
            isExpandedOptionsCompleted = false,
        )

        assertEndConditionsDerivedState(
            state = state,
            expectedIsNotStarted = true,
            expectedIsExpandedCompleted = false,
        )
    }

    @Test
    fun `EndConditions derived state is correct when only conditions step is completed`() {
        val state = GameStartState.EndConditions(
            isExpandedConditionsCompleted = true,
            isExpandedOptionsCompleted = false,
        )

        assertEndConditionsDerivedState(
            state = state,
            expectedIsNotStarted = false,
            expectedIsExpandedCompleted = false,
        )
    }

    @Test
    fun `EndConditions derived state is correct when only options step is completed`() {
        val state = GameStartState.EndConditions(
            isExpandedConditionsCompleted = false,
            isExpandedOptionsCompleted = true,
        )

        assertEndConditionsDerivedState(
            state = state,
            expectedIsNotStarted = false,
            expectedIsExpandedCompleted = false,
        )
    }

    @Test
    fun `EndConditions derived state is correct when both steps are completed`() {
        val state = GameStartState.EndConditions(
            isExpandedConditionsCompleted = true,
            isExpandedOptionsCompleted = true,
        )

        assertEndConditionsDerivedState(
            state = state,
            expectedIsNotStarted = false,
            expectedIsExpandedCompleted = true,
        )
    }

    private fun assertEndConditionsDerivedState(
        state: GameStartState.EndConditions,
        expectedIsNotStarted: Boolean,
        expectedIsExpandedCompleted: Boolean,
    ) {
        assertEquals(expectedIsNotStarted, state.isNotStarted)
        assertEquals(expectedIsExpandedCompleted, state.isExpandedCompleted)
    }
}
