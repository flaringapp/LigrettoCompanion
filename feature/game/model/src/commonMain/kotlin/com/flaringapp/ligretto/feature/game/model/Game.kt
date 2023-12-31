package com.flaringapp.ligretto.feature.game.model

import com.flaringapp.ligretto.feature.game.model.end.GameEndConditions
import com.flaringapp.ligretto.feature.player.model.Player
import kotlinx.datetime.Instant

data class Game(
    val id: GameId,
    val players: List<Player>,
    val timeStarted: Instant,
    val scores: Map<Player, Score> = players.associateWith { Score.Zero },
    val completedLaps: List<Lap> = emptyList(),
    val endConditions: GameEndConditions = GameEndConditions(),
) {

    init {
        require(players.isNotEmpty()) { "Game must have players!" }
        require(scores.keys == players.toSet()) { "Game must have scores for all players!" }
    }

    val lastLap: Lap?
        get() = completedLaps.lastOrNull()

    val completedLapsCount: Int
        get() = completedLaps.size

    val pendingLapNumber: Int
        get() = completedLapsCount + 1

    val matchesEndConditions: Boolean
        get() = endConditions.matches(this)

    fun withNewLap(
        lap: Lap,
        newScores: Map<Player, Score>,
    ): Game {
        require(lap.cardsOnTable.keys == newScores.keys)
        require(lap.cardsLeft.keys == newScores.keys)
        require(newScores.keys == players.toSet())

        return copy(
            scores = newScores,
            completedLaps = completedLaps + lap,
        )
    }
}
