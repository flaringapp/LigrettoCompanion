package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.model.Game
import com.flaringapp.ligretto.core.domain.model.GameConfig
import com.flaringapp.ligretto.core.model.GameId
import com.flaringapp.ligretto.core.model.end.GameEndConditions
import com.flaringapp.ligretto.core.model.end.GameEndScoreCondition
import com.flaringapp.ligretto.core.model.end.GameEndTimeCondition
import com.flaringapp.ligretto.domain.contracts.GameRepository
import org.koin.core.annotation.Single
import kotlinx.datetime.Clock

interface StartGameUseCase {

    operator fun invoke(config: GameConfig)
}

@Single
internal class StartGameUseCaseImpl(
    private val repository: GameRepository,
    private val clock: Clock,
) : StartGameUseCase {

    override fun invoke(config: GameConfig) {
        val endConditions = GameEndConditions(
            score = config.targetScore?.let { GameEndScoreCondition(it) },
            time = config.timeLimit?.let { GameEndTimeCondition(it, clock) },
        )

        val game = Game(
            id = GameId.zero(),
            players = config.players,
            timeStarted = clock.now(),
            endConditions = endConditions,
        )

        repository.startGame(game)
    }
}
