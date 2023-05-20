package com.flaringapp.ligretto.feature.game.domain

import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.Lap
import com.flaringapp.ligretto.feature.game.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import org.koin.core.annotation.Factory

internal interface GameLapApplier {

    fun apply(game: Game, lap: Lap): Game
}

@Factory
internal class GameLapApplierImpl(
    private val scoreCalculator: GameScoreCalculator,
) : GameLapApplier {

    override fun apply(game: Game, lap: Lap): Game {
        val newPlayersScores = resolvePlayersScoresAfterLap(game, lap)
        return game.withNewLap(
            lap = lap,
            newScores = newPlayersScores,
        )
    }

    private fun resolvePlayersScoresAfterLap(
        game: Game,
        lap: Lap,
    ): Map<Player, Score> {
        return game.players.associateWith { player ->
            resolvePlayerScoreAfterLap(game, lap, player)
        }
    }

    private fun resolvePlayerScoreAfterLap(
        game: Game,
        lap: Lap,
        player: Player,
    ): Score {
        val lapScore = resolvePlayerLapScore(lap, player)
        val currentScore = game.scores[player] ?: Score.Zero

        return currentScore + lapScore
    }

    private fun resolvePlayerLapScore(lap: Lap, player: Player): Score {
        val cardsLeftCount = lap.cardsLeft[player] ?: 0
        val cardsLeftScore = scoreCalculator.getCardsLeftScore(cardsLeftCount)

        val cardsOnTableCount = lap.cardsOnTable[player] ?: 0
        val cardsOnTableScore = scoreCalculator.getCardsOnTableScore(cardsOnTableCount)

        return scoreCalculator.getLapScore(
            cardsLeftScore = cardsLeftScore,
            cardsOnTableScore = cardsOnTableScore,
        )
    }
}
