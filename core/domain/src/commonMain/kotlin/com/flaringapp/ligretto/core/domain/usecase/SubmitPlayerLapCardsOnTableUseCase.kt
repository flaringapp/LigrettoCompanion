package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.domain.GameStorage
import com.flaringapp.ligretto.core.model.Player
import org.koin.core.annotation.Single

interface SubmitPlayerLapCardsOnTableUseCase {

    operator fun invoke(player: Player, count: Int)
}

@Single
internal class SubmitPlayerLapCardsOnTableUseCaseImpl(
    private val gameStorage: GameStorage,
) : SubmitPlayerLapCardsOnTableUseCase {

    override fun invoke(player: Player, count: Int) {
        val lap = requireNotNull(gameStorage.lapFlow.value)

        val newCardsOnTable = lap.cardsOnTable.toMutableMap()
        newCardsOnTable[player] = count

        val newLap = lap.copy(cardsOnTable = newCardsOnTable)

        gameStorage.lapFlow.value = newLap
    }
}
