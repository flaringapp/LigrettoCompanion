package com.flaringapp.ligretto.android.ui.feature.game.score

import com.flaringapp.ligretto.android.ui.mvi.MviViewModel
import com.flaringapp.ligretto.android.ui.mvi.dispatch
import com.flaringapp.ligretto.model.Game
import com.flaringapp.ligretto.model.Score
import com.flaringapp.ligretto.usecase.CheckGameEndConditionsUseCase
import com.flaringapp.ligretto.usecase.GetCurrentGameUseCase
import com.flaringapp.ligretto.usecase.StartLapUseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class GameScoreViewModel(
    private val getCurrentGameUseCase: GetCurrentGameUseCase,
    private val checkGameEndConditionsUseCase: CheckGameEndConditionsUseCase,
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
        val isGameEnded = game?.let(checkGameEndConditionsUseCase::invoke) ?: false
        if (isGameEnded) {
            setEffect { GameScoreEffect.EndGame }
            return@also
        }

        startLapUseCase()
        setEffect { GameScoreEffect.OpenNextLap }
    }
}
