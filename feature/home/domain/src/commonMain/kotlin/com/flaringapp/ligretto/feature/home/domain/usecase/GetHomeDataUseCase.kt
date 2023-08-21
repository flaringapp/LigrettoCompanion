package com.flaringapp.ligretto.feature.home.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.home.domain.model.HomeData
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

interface GetHomeDataUseCase {

    suspend operator fun invoke(): Flow<HomeData>
}

@Single
internal class GetHomeDataUseCaseImpl(
    private val repository: GameRepository,
) : GetHomeDataUseCase {

    override suspend fun invoke(): Flow<HomeData> {
        return combine(
            repository.previousGameFlow,
            repository.observeActiveGameId(),
        ) { previousGame, activeGameId ->
            HomeData(
                activeGame = previousGame?.takeIf { it.game.id == activeGameId },
                previousGame = previousGame,
            )
        }
    }
}
