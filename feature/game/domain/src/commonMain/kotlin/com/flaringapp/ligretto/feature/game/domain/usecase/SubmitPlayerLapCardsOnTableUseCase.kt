package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Player
import org.koin.core.annotation.Single

interface SubmitPlayerLapCardsOnTableUseCase {

    suspend operator fun invoke(player: Player, count: Int)
}

@Single
internal class SubmitPlayerLapCardsOnTableUseCaseImpl(
    private val repository: GameRepository,
) : SubmitPlayerLapCardsOnTableUseCase {

    override suspend fun invoke(player: Player, count: Int) {
        val lap = requireNotNull(repository.currentLapFlow.value)

        val newCardsOnTable = lap.cardsOnTable.toMutableMap()
        newCardsOnTable[player] = count

        val newLap = lap.copy(cardsOnTable = newCardsOnTable)

        repository.updateLapPlayerCards(
            lap = newLap,
            player = player,
        )
    }
}
