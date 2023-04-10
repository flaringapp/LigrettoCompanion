package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.GameIdProvider
import com.flaringapp.ligretto.GameStorage
import com.flaringapp.ligretto.core.model.Game
import com.flaringapp.ligretto.core.model.GameConfig
import com.flaringapp.ligretto.core.model.end.GameEndConditions
import com.flaringapp.ligretto.core.model.end.GameEndScoreCondition
import com.flaringapp.ligretto.core.model.end.GameEndTimeCondition
import org.koin.core.annotation.Single
import kotlinx.datetime.Clock

interface StartGameUseCase {

    operator fun invoke(config: GameConfig)
}

@Single
internal class StartGameUseCaseImpl(
    private val gameIdProvider: GameIdProvider,
    private val gameStorage: GameStorage,
    private val clock: Clock,
) : StartGameUseCase {

    override fun invoke(config: GameConfig) {
        val id = gameIdProvider.provide()

        val endConditions = GameEndConditions(
            score = config.targetScore?.let { GameEndScoreCondition(it) },
            time = config.timeLimit?.let { GameEndTimeCondition(it, clock) },
        )

        val game = Game(
            id = id,
            players = config.players,
            timeStarted = clock.now(),
            endConditions = endConditions,
        )

        gameStorage.gameFlow.value = game
    }
}
