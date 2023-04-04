package com.flaringapp.ligretto.model.end

import com.flaringapp.ligretto.model.Game
import com.flaringapp.ligretto.model.GameId
import com.flaringapp.ligretto.model.Player
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.datetime.Instant

internal class GameEndCompositeConditionTest {

    @Test
    fun `returns true given there are no conditions`() {
        val condition = GameEndCompositeCondition(emptyList())

        assertTrue(condition.matches(mockGame()))
    }

    @Test
    fun `returns true given one condition is true`() {
        val condition = GameEndCompositeCondition(
            listOf(GameEndTrueCondition),
        )

        assertTrue(condition.matches(mockGame()))
    }

    @Test
    fun `returns false given one condition is false`() {
        val condition = GameEndCompositeCondition(
            listOf(GameEndFalseCondition),
        )

        assertFalse(condition.matches(mockGame()))
    }

    @Test
    fun `returns true given multiple conditions are all true`() {
        val condition = GameEndCompositeCondition(
            List(3) { GameEndTrueCondition },
        )

        assertTrue(condition.matches(mockGame()))
    }


    @Test
    fun `returns false given any of multiple conditions is false`() {
        val condition = GameEndCompositeCondition(
            listOf(
                GameEndFalseCondition,
                GameEndTrueCondition,
                GameEndTrueCondition,
                GameEndTrueCondition,
            )
        )

        assertFalse(condition.matches(mockGame()))
    }

    private fun mockGame(): Game {
        val players = listOf(
            Player(1, "Andreo"),
            Player(2, "Mario"),
        )
        return Game(
            id = GameId(1),
            players = players,
            timeStarted = Instant.DISTANT_PAST,
        )
    }

    private object GameEndTrueCondition : GameEndCondition {
        override fun matches(game: Game): Boolean = true
    }

    private object GameEndFalseCondition : GameEndCondition {
        override fun matches(game: Game): Boolean = false
    }
}
