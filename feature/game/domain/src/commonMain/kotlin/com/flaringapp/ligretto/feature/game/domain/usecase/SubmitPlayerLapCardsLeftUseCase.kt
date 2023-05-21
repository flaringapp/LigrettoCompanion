package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Player
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
