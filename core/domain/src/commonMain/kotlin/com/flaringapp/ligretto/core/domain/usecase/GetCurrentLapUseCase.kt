package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.model.Lap
import com.flaringapp.ligretto.domain.contracts.GameRepository
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
