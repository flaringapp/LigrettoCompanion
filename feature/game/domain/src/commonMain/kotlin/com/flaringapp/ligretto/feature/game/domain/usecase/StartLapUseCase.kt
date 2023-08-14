package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Lap
import org.koin.core.annotation.Single

interface StartLapUseCase {

    suspend operator fun invoke(): Lap
}

@Single
internal class StartLapUseCaseImpl(
    private val repository: GameRepository,
) : StartLapUseCase {

    override suspend fun invoke(): Lap {
        return repository.startNextLap()
    }
}
