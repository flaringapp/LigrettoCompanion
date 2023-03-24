package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.GameStorage
import com.flaringapp.ligretto.model.Player
import org.koin.core.annotation.Single

interface SubmitPlayerLapCardsOnTableUseCase {

    operator fun invoke(player: Player, count: Int)
}

@Single
internal class SubmitPlayerLapCardsOnTableUseCaseImpl(
    private val gameStorage: GameStorage,
) : SubmitPlayerLapCardsLeftUseCase {

    override fun invoke(player: Player, count: Int) {
        val lap = requireNotNull(gameStorage.lapFlow.value)

        val newCardsOnTable = lap.cardsOnTable.toMutableMap()
        newCardsOnTable[player] = count

        val newLap = lap.copy(cardsOnTable = newCardsOnTable)

        gameStorage.lapFlow.value = newLap
    }
}
