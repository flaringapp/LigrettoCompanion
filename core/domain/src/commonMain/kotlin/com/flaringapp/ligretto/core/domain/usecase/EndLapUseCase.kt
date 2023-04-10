package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.domain.GameLapApplier
import com.flaringapp.ligretto.core.domain.GameStorage
import com.flaringapp.ligretto.core.model.Game
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
