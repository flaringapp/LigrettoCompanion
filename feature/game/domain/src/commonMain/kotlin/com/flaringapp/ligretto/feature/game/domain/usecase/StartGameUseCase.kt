package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.GameConfig
import org.koin.core.annotation.Single

interface StartGameUseCase {

    suspend operator fun invoke(config: GameConfig)
}

@Single
internal class StartGameUseCaseImpl(
    private val repository: GameRepository,
) : StartGameUseCase {

    override suspend fun invoke(config: GameConfig) {
        repository.startGame(config)
    }
}
