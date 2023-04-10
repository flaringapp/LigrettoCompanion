package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.domain.GameStorage
import com.flaringapp.ligretto.core.model.Lap
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface GetCurrentLapUseCase {

    operator fun invoke(): StateFlow<Lap?>
}

@Single
internal class GetCurrentLapUseCaseImpl(
    private val gameStorage: GameStorage,
) : GetCurrentLapUseCase {

    override fun invoke(): StateFlow<Lap?> {
        return gameStorage.lapFlow.asStateFlow()
    }
}
