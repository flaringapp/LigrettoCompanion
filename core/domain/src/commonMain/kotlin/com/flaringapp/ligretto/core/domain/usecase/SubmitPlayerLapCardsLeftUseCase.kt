package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.model.Player
import com.flaringapp.ligretto.domain.contracts.GameRepository
import org.koin.core.annotation.Single

interface SubmitPlayerLapCardsLeftUseCase {

    operator fun invoke(player: Player, count: Int)
}

@Single
internal class SubmitPlayerLapCardsLeftUseCaseImpl(
    private val repository: GameRepository,
) : SubmitPlayerLapCardsLeftUseCase {

    override fun invoke(player: Player, count: Int) {
        val lap = requireNotNull(repository.currentLapFlow.value)

        val newCardsLeft = lap.cardsLeft.toMutableMap()
        newCardsLeft[player] = count

        val newLap = lap.copy(cardsLeft = newCardsLeft)

        repository.updateLapCards(newLap)
    }
}
