package com.flaringapp.ligretto.feature.game.ui.end

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
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
        is GameEndIntent.InitData -> initData(intent.winners)
        GameEndIntent.Finish -> finish()
    }

    private fun loadData() = state.also {
        val game = endGameUseCase() ?: return@also
        val winners = mapWinners(game) ?: return@also

        dispatch { GameEndIntent.InitData(winners) }
    }

    private fun mapWinners(game: Game): GameEndState.Winners? {
        val scoreboard = game.scores.entries.asSequence()
            .sortedByDescending { (_, score) -> score.value }
            .map { (player, score) ->
                GameEndState.PlayerResult(
                    name = player.name,
                    score = score.value,
                )
            }
            .toList()

        return GameEndState.Winners(
            firstPlace = scoreboard.firstOrNull() ?: return null,
            secondPlace = scoreboard.getOrNull(1),
            thirdPlace = scoreboard.getOrNull(2),
            otherPlaces = scoreboard.drop(3).asUiList(),
        )
    }

    private fun initData(winners: GameEndState.Winners) = updateState {
        copy(winners = winners)
    }

    private fun finish() = state.also {
        setEffect { GameEndEffect.Close }
    }
}
