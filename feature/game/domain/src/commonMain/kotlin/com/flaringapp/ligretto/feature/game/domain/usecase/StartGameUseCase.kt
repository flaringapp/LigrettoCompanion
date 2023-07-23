package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameId
import com.flaringapp.ligretto.feature.game.model.end.GameEndConditions
import com.flaringapp.ligretto.feature.game.model.end.GameEndScoreCondition
import com.flaringapp.ligretto.feature.game.model.end.GameEndTimeCondition
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
