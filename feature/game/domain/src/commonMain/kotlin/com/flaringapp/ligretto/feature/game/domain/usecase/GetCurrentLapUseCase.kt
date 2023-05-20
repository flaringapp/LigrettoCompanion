package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Lap
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.StateFlow

interface GetCurrentLapUseCase {

    operator fun invoke(): StateFlow<Lap?>
}

@Single
internal class GetCurrentLapUseCaseImpl(
    private val repository: GameRepository,
) : GetCurrentLapUseCase {

    override fun invoke(): StateFlow<Lap?> {
        return repository.currentLapFlow
    }
}
