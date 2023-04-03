package com.flaringapp.ligretto.android.ui.feature.game.end

import com.flaringapp.ligretto.android.ui.mvi.MviViewModel
import com.flaringapp.ligretto.android.ui.mvi.dispatch
import com.flaringapp.ligretto.model.Player
import com.flaringapp.ligretto.model.Score
import com.flaringapp.ligretto.usecase.EndGameUseCase
import com.flaringapp.ligretto.usecase.GetCurrentGameUseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class GameEndViewModel(
    private val getCurrentGameUseCase: GetCurrentGameUseCase,
    private val endGameUseCase: EndGameUseCase,
) : MviViewModel<GameEndState, GameEndIntent, GameEndEffect>(GameEndState()) {

    init {
        loadGame()
    }

    override fun reduce(
        state: GameEndState,
        intent: GameEndIntent,
    ): GameEndState = when (intent) {
        is GameEndIntent.InitData -> initData(intent.winners)
        GameEndIntent.Finish -> finish()
    }

    private fun loadGame() {
        val game = getCurrentGameUseCase().value ?: return
        val leaders = game.scores.entries.asSequence()
            .sortedByDescending { (_, score) -> score.value }
            .take(3)
            .toList()

        val firstPlace = leaders.firstOrNull()?.let { (player, score) ->
            mapToUi(player, score)
        } ?: return
        val secondPlace = leaders.getOrNull(1)?.let { (player, score) ->
            mapToUi(player, score)
        }
        val thirdPlace = leaders.getOrNull(2)?.let { (player, score) ->
            mapToUi(player, score)
        }
        val winners = GameEndState.Winners(
            firstPlace = firstPlace,
            secondPlace = secondPlace,
            thirdPlace = thirdPlace,
        )

        dispatch { GameEndIntent.InitData(winners) }
    }

    private fun mapToUi(player: Player, score: Score): GameEndState.PlayerResult {
        return GameEndState.PlayerResult(
            name = player.name,
            score = score.value,
        )
    }

    private fun initData(winners: GameEndState.Winners) = updateState {
        copy(winners = winners)
    }

    private fun finish() = state.also {
        endGameUseCase()
        setEffect { GameEndEffect.Close }
    }
}
