package com.flaringapp.ligretto.android.ui.feature.game.score

import com.flaringapp.ligretto.android.ui.mvi.MviViewModel
import com.flaringapp.ligretto.android.ui.mvi.dispatch
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
        loadScores()
    }

    override fun reduce(
        state: GameScoreState,
        intent: GameScoreIntent,
    ): GameScoreState = when (intent) {
        is GameScoreIntent.InitData -> initData(intent.scores)
        GameScoreIntent.StartNextLap -> startNewLap()
    }

    private fun loadScores() {
        val game = getCurrentGameUseCase().value ?: return
        val scores = game.players.map { player ->
            val score = game.scores[player] ?: Score.Zero
            GameScoreState.PlayerScore(
                id = player.id,
                playerName = player.name,
                score = score.value,
            )
        }

        dispatch { GameScoreIntent.InitData(scores) }
    }

    private fun initData(scores: List<GameScoreState.PlayerScore>) = updateState {
        copy(scores = scores)
    }

    private fun startNewLap(): GameScoreState = state.also {
        startLapUseCase()
        setEffect { GameScoreEffect.OpenNextLap }
    }
}
