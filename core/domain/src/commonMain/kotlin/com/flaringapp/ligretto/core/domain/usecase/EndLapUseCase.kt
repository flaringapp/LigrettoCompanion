package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.domain.GameLapApplier
import com.flaringapp.ligretto.core.model.Game
import com.flaringapp.ligretto.domain.contracts.GameRepository
import org.koin.core.annotation.Single

interface EndLapUseCase {

    operator fun invoke(): Game?
}

@Single
internal class EndLapUseCaseImpl(
    private val repository: GameRepository,
    private val gameLapApplier: GameLapApplier,
) : EndLapUseCase {

    override fun invoke(): Game? {
        val game = repository.currentGameFlow.value ?: return null
        val lap = repository.currentLapFlow.value ?: return null

        val gameWithNewLap = gameLapApplier.apply(game, lap)

        repository.setCurrentGame(gameWithNewLap)
        repository.setCurrentLap(null)

        return gameWithNewLap
    }
}
