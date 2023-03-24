package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.GameScoreCalculator
import com.flaringapp.ligretto.GameStorage
import com.flaringapp.ligretto.model.Game
import com.flaringapp.ligretto.model.Lap
import com.flaringapp.ligretto.model.Player
import com.flaringapp.ligretto.model.Score
import org.koin.core.annotation.Single

interface EndLapUseCase {

    operator fun invoke(): Lap?
}

@Single
internal class EndLapUseCaseImpl(
    private val gameStorage: GameStorage,
    private val scoreCalculator: GameScoreCalculator,
) : EndLapUseCase {

    override fun invoke(): Lap? {
        val game = gameStorage.gameFlow.value ?: return null
        val lap = gameStorage.lapFlow.value ?: return null

        val newPlayersScores = resolvePlayersScoresAfterLap(game, lap)

        val gameWithNewLap = game.withNewLap(
            lap = lap,
            newScores = newPlayersScores,
        )

        gameStorage.gameFlow.value = gameWithNewLap
        gameStorage.lapFlow.value = null

        return lap
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
