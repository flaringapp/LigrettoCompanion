package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.GameStorage
import com.flaringapp.ligretto.model.Lap
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
