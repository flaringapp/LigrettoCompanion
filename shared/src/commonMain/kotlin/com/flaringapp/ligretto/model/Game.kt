package com.flaringapp.ligretto.model

data class Game(
    val id: GameId,
    val players: List<Player>,
    val scores: Map<Player, Score>,
    val completedLaps: List<Lap>,
) {

    init {
        require(players.isNotEmpty()) { "Game must have players!" }
        require(scores.keys == players.toSet()) { "Game must have scores for all players!" }
    }

    val completedLapsCount: Int
        get() = completedLaps.size

    val pendingLapNumber: Int
        get() = completedLapsCount + 1

    internal fun withNewLap(
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