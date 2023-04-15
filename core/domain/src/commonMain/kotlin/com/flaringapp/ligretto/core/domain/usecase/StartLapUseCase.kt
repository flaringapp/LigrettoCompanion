package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.domain.GameStorage
import com.flaringapp.ligretto.core.model.Lap
import org.koin.core.annotation.Single

interface StartLapUseCase {

    operator fun invoke(): Lap
}

@Single
internal class StartLapUseCaseImpl(
    private val gameStorage: GameStorage,
) : StartLapUseCase {

    override fun invoke(): Lap {
        val game = requireNotNull(gameStorage.gameFlow.value)
        val lap = Lap.empty(
            number = game.pendingLapNumber,
            players = game.players,
        )

        gameStorage.lapFlow.value = lap

        return lap
    }
}