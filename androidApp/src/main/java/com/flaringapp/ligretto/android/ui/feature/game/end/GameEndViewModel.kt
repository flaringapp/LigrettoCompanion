package com.flaringapp.ligretto.android.ui.feature.game.end

import com.flaringapp.ligretto.android.ui.mvi.MviViewModel
import com.flaringapp.ligretto.android.ui.mvi.dispatch
import com.flaringapp.ligretto.model.Game
import com.flaringapp.ligretto.usecase.EndGameUseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class GameEndViewModel(
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
        val leaders = game.scores.entries.asSequence()
            .sortedByDescending { (_, score) -> score.value }
            .take(3)
            .map { (player, score) ->
                GameEndState.PlayerResult(
                    name = player.name,
                    score = score.value,
                )
            }
            .toList()

        return GameEndState.Winners(
            firstPlace = leaders.firstOrNull() ?: return null,
            secondPlace = leaders.getOrNull(1),
            thirdPlace = leaders.getOrNull(2),
        )
    }

    private fun initData(winners: GameEndState.Winners) = updateState {
        copy(winners = winners)
    }

    private fun finish() = state.also {
        setEffect { GameEndEffect.Close }
    }
}
