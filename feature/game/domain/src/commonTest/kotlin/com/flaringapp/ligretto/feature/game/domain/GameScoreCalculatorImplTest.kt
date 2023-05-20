package com.flaringapp.ligretto.feature.game.domain

import com.flaringapp.ligretto.feature.game.model.Score
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class GameScoreCalculatorImplTest {

    private lateinit var calculator: GameScoreCalculator

    @BeforeTest
    fun before() {
        calculator = GameScoreCalculatorImpl()
    }

    @Test
    fun getCardsLeftScore() {
        val actual = calculator.getCardsLeftScore(5)
        assertEquals(Score(10), actual)
    }

    @Test
    fun getCardsOnTableScore() {
        val actual = calculator.getCardsOnTableScore(7)
        assertEquals(Score(7), actual)
    }

    @Test
    fun getLapScore() {
        val actual = calculator.getLapScore(
            cardsLeftScore = Score(8),
            cardsOnTableScore = Score(22),
        )
        assertEquals(Score(14), actual)
    }
}
