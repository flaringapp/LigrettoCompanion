package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.model.Player
import com.flaringapp.ligretto.domain.contracts.GameRepository
import org.koin.core.annotation.Single

interface SubmitPlayerLapCardsOnTableUseCase {

    operator fun invoke(player: Player, count: Int)
}

@Single
internal class SubmitPlayerLapCardsOnTableUseCaseImpl(
    private val repository: GameRepository,
) : SubmitPlayerLapCardsOnTableUseCase {

    override fun invoke(player: Player, count: Int) {
        val lap = requireNotNull(repository.currentLapFlow.value)

        val newCardsOnTable = lap.cardsOnTable.toMutableMap()
        newCardsOnTable[player] = count

        val newLap = lap.copy(cardsOnTable = newCardsOnTable)

        repository.updateLapCards(newLap)
    }
}
