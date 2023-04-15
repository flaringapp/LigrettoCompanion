package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.model.Game
import com.flaringapp.ligretto.domain.contracts.GameRepository
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.StateFlow

interface GetCurrentGameUseCase {

    operator fun invoke(): StateFlow<Game?>
}

@Single
internal class GetCurrentGameUseCaseImpl(
    private val repository: GameRepository,
) : GetCurrentGameUseCase {

    override fun invoke(): StateFlow<Game?> {
        return repository.currentGameFlow
    }
}
