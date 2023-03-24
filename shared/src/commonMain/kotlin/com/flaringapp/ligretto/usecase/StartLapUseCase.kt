package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.GameStorage
import com.flaringapp.ligretto.model.Lap
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
