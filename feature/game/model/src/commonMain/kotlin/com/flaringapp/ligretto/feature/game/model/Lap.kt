package com.flaringapp.ligretto.feature.game.model

data class Lap(
    val id: LapId,
    val number: Int,
    val cardsLeft: Map<Player, Int> = emptyMap(),
    val cardsOnTable: Map<Player, Int> = emptyMap(),
) {

    companion object {
        fun empty(id: LapId, number: Int, players: List<Player>): Lap {
            val cardsCount = players.associateWith { 0 }
            return Lap(
                id = id,
                number = number,
                cardsLeft = cardsCount,
                cardsOnTable = cardsCount,
            )
        }
    }

    init {
        require(cardsLeft.isNotEmpty() && cardsLeft.keys == cardsOnTable.keys) {
            "Lap cards count should contain information about all players"
        }
    }
}
