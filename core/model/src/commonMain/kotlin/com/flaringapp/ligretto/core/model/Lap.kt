package com.flaringapp.ligretto.core.model

data class Lap(
    val number: Int,
    val cardsLeft: Map<Player, Int> = emptyMap(),
    val cardsOnTable: Map<Player, Int> = emptyMap(),
) {

    companion object {
        fun empty(number: Int, players: List<Player>): Lap {
            val cardsCount = players.associateWith { 0 }
            return Lap(
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
