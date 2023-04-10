package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.domain.GameStorage
import com.flaringapp.ligretto.core.model.Player
import org.koin.core.annotation.Single

interface SubmitPlayerLapCardsLeftUseCase {

    operator fun invoke(player: Player, count: Int)
}

@Single
internal class SubmitPlayerLapCardsLeftUseCaseImpl(
    private val gameStorage: GameStorage,
) : SubmitPlayerLapCardsLeftUseCase {

    override fun invoke(player: Player, count: Int) {
        val lap = requireNotNull(gameStorage.lapFlow.value)

        val newCardsLeft = lap.cardsLeft.toMutableMap()
        newCardsLeft[player] = count

        val newLap = lap.copy(cardsLeft = newCardsLeft)

        gameStorage.lapFlow.value = newLap
    }
}
