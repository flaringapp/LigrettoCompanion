package com.flaringapp.ligretto.android.ui.feature.game.score

import com.flaringapp.ligretto.android.ui.mvi.MviViewModel
import com.flaringapp.ligretto.android.ui.mvi.dispatch
import com.flaringapp.ligretto.model.Game
import com.flaringapp.ligretto.model.Score
import com.flaringapp.ligretto.usecase.GetCurrentGameUseCase
import com.flaringapp.ligretto.usecase.StartLapUseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class GameScoreViewModel(
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
        is GameScoreIntent.InitData -> initData(intent.scores)
        GameScoreIntent.StartNextLap -> startNewLap()
    }

    private fun loadData() = state.also {
        val game = getCurrentGameUseCase().value ?: return@also
        val scores = mapScores(game)

        dispatch { GameScoreIntent.InitData(scores) }
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

    private fun initData(scores: List<GameScoreState.PlayerScore>) = updateState {
        copy(scores = scores)
    }

    private fun startNewLap(): GameScoreState = state.also {
        startLapUseCase()
        setEffect { GameScoreEffect.OpenNextLap }
    }
}
