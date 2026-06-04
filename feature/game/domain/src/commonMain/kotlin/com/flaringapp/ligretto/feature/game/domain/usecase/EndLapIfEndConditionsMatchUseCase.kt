package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.core.di.DispatcherType
import com.flaringapp.ligretto.feature.game.domain.GameLapApplier
import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Game
import org.koin.core.annotation.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface EndLapIfEndConditionsMatchUseCase {

    suspend operator fun invoke(): EndLapIfEndConditionsMatchResult?
}

data class EndLapIfEndConditionsMatchResult(
    val game: Game,
    val lapEnded: Boolean,
)

@Single
internal class EndLapIfEndConditionsMatchUseCaseImpl(
    @DispatcherType.Default
    private val defaultDispatcher: CoroutineDispatcher,
    private val repository: GameRepository,
    private val gameLapApplier: GameLapApplier,
) : EndLapIfEndConditionsMatchUseCase {

    override suspend fun invoke(): EndLapIfEndConditionsMatchResult? {
        val game = repository.currentGameFlow.value ?: return null
        val lap = repository.currentLapFlow.value ?: return null

        return withContext(defaultDispatcher) {
            val gameWithNewLap = gameLapApplier.apply(game, lap)

            if (!gameWithNewLap.matchesEndConditions) {
                return@withContext EndLapIfEndConditionsMatchResult(
                    game = gameWithNewLap,
                    lapEnded = false,
                )
            }

            repository.endLap(gameWithNewLap)

            EndLapIfEndConditionsMatchResult(
                game = gameWithNewLap,
                lapEnded = true,
            )
        }
    }
}
