package com.flaringapp.ligretto

import com.flaringapp.ligretto.model.Score
import org.koin.core.annotation.Single

internal interface GameScoreCalculator {

    fun getCardsLeftScore(value: Int): Score

    fun getCardsOnTableScore(value: Int): Score

    fun getLapScore(
        cardsLeftScore: Score,
        cardsOnTableScore: Score,
    ): Score
}

@Single
internal class GameScoreCalculatorImpl : GameScoreCalculator {

    override fun getCardsLeftScore(value: Int): Score {
        return Score(value * 2)
    }

    override fun getCardsOnTableScore(value: Int): Score {
        return Score(value)
    }

    override fun getLapScore(cardsLeftScore: Score, cardsOnTableScore: Score): Score {
        return cardsOnTableScore - cardsLeftScore
    }
}
