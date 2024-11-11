package com.flaringapp.ligretto.feature.game.ui.end

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.asUiList
import com.flaringapp.ligretto.feature.game.domain.usecase.EndGameUseCase
import com.flaringapp.ligretto.feature.game.model.Game
import org.koin.android.annotation.KoinViewModel

@KoinViewModel(binds = [])
internal class GameEndViewModel(
    private val endGameUseCase: EndGameUseCase,
) : MviViewModel<GameEndState, GameEndIntent, GameEndEffect>(GameEndState()) {

    init {
        dispatch { GameEndIntent.LoadData }
    }

    override fun reduce(
        state: GameEndState,
        intent: GameEndIntent,
    ): GameEndState = when (intent) {
        GameEndIntent.LoadData -> loadData()
        is GameEndIntent.InitData -> initData(intent.scoreboard)
        GameEndIntent.Finish -> finish()
    }

    private fun loadData() = state.also {
        val game = endGameUseCase() ?: return@also
        val scoreboard = mapScoreboard(game)

        dispatch { GameEndIntent.InitData(scoreboard) }
    }

    private fun mapScoreboard(game: Game): UiList<GameEndState.PlayerResult> {
        return game.scores.entries.asSequence()
            .sortedByDescending { (_, score) -> score.value }
            .map { (player, score) ->
                GameEndState.PlayerResult(
                    name = player.name,
                    score = score.value,
                )
            }
            .toList()
            .asUiList()
    }

    private fun initData(scoreboard: UiList<GameEndState.PlayerResult>) = updateState {
        copy(scoreboard = scoreboard)
    }

    private fun finish() = state.also {
        setEffect { GameEndEffect.Close }
    }
}
