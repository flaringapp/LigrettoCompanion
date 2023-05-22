package com.flaringapp.ligretto.feature.game.ui.score

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentGameUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.StartLapUseCase
import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.Score
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class GameScoreViewModel(
    private val getCurrentGameUseCase: GetCurrentGameUseCase,
    private val startLapUseCase: StartLapUseCase,
) : MviViewModel<GameScoreState, GameScoreIntent, GameScoreEffect>(GameScoreState()) {

    init {
        dispatch { GameScoreIntent.LoadData }
    }

    override fun reduce(
        state: GameScoreState,
        intent: GameScoreIntent,
    ): GameScoreState = when (intent) {
        GameScoreIntent.LoadData -> loadData()
        is GameScoreIntent.InitData -> initData(
            scores = intent.scores,
            endConditions = intent.endConditions,
        )
        GameScoreIntent.StartNextLap -> startNewLap()
    }

    private fun loadData() = state.also {
        val game = getCurrentGameUseCase().value ?: return@also
        val scores = mapScores(game)
        val endConditions = mapEndConditions(game)

        dispatch {
            GameScoreIntent.InitData(
                scores = scores,
                endConditions = endConditions,
            )
        }
    }

    private fun mapScores(game: Game): List<GameScoreState.PlayerScore> {
        return game.players.map { player ->
            val score = game.scores[player] ?: Score.Zero
            GameScoreState.PlayerScore(
                id = player.id,
                playerName = player.name,
                score = score.value,
            )
        }
    }

    private fun mapEndConditions(game: Game): GameScoreState.EndConditions? {
        if (game.endConditions.isEmpty) return null

        val score = game.endConditions.score?.targetScore?.value
        val time = game.endConditions.time?.let {
            GameScoreState.EndConditions.Time(
                timeEnd = game.timeStarted + it.gameDuration,
                clock = it.clock,
            )
        }

        return GameScoreState.EndConditions(
            score = score,
            time = time,
        )
    }

    private fun initData(
        scores: List<GameScoreState.PlayerScore>,
        endConditions: GameScoreState.EndConditions?,
    ) = updateState {
        copy(
            scores = scores,
            endConditions = endConditions,
        )
    }

    private fun startNewLap(): GameScoreState = state.also {
        val game = getCurrentGameUseCase().value
        if (game?.matchesEndConditions == true) {
            setEffect { GameScoreEffect.EndGame }
            return@also
        }

        startLapUseCase()
        setEffect { GameScoreEffect.OpenNextLap }
    }
}
