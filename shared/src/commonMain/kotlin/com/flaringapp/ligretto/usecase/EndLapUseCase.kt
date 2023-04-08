package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.GameLapApplier
import com.flaringapp.ligretto.GameStorage
import com.flaringapp.ligretto.model.Game
import org.koin.core.annotation.Single

interface EndLapUseCase {

    operator fun invoke(): Game?
}

@Single
internal class EndLapUseCaseImpl(
    private val gameStorage: GameStorage,
    private val gameLapApplier: GameLapApplier,
) : EndLapUseCase {

    override fun invoke(): Game? {
        val game = gameStorage.gameFlow.value ?: return null
        val lap = gameStorage.lapFlow.value ?: return null

        val gameWithNewLap = gameLapApplier.apply(game, lap)

        gameStorage.gameFlow.value = gameWithNewLap
        gameStorage.lapFlow.value = null

        return gameWithNewLap
    }
}
