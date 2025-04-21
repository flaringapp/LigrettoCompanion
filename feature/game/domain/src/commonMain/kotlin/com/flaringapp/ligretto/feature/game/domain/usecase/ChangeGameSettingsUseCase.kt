package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Score
import org.koin.core.annotation.Single
import kotlin.time.Duration

interface ChangeGameSettingsUseCase {

    suspend operator fun invoke(
        targetScore: Score? = null,
        timeLimit: Duration? = null,
    )
}

@Single
internal class ChangeGameSettingsUseCaseImpl(
    private val repository: GameRepository,
) : ChangeGameSettingsUseCase {

    override suspend fun invoke(
        targetScore: Score?,
        timeLimit: Duration?,
    ) {
        repository.changeGameSettings(
            targetScore = targetScore,
            timeLimit = timeLimit,
        )
    }
}
