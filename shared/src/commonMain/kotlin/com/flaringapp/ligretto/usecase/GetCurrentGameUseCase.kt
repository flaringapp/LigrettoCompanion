package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.GameStorage
import com.flaringapp.ligretto.core.model.Game
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface GetCurrentGameUseCase {

    operator fun invoke(): StateFlow<Game?>
}

@Single
internal class GetCurrentGameUseCaseImpl(
    private val gameStorage: GameStorage,
) : GetCurrentGameUseCase {

    override fun invoke(): StateFlow<Game?> {
        return gameStorage.gameFlow.asStateFlow()
    }
}
