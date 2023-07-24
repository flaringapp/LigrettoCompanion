package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.GameLapApplier
import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Game
import org.koin.core.annotation.Single

interface EndLapUseCase {

    suspend operator fun invoke(): Game?
}

@Single
internal class EndLapUseCaseImpl(
    private val repository: GameRepository,
    private val gameLapApplier: GameLapApplier,
) : EndLapUseCase {

    override suspend fun invoke(): Game? {
        val game = repository.currentGameFlow.value ?: return null
        val lap = repository.currentLapFlow.value ?: return null

        val gameWithNewLap = gameLapApplier.apply(game, lap)

        repository.endLap(gameWithNewLap)

        return gameWithNewLap
    }
}
