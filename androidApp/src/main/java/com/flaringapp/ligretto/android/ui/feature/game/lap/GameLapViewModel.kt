package com.flaringapp.ligretto.android.ui.feature.game.lap

import androidx.lifecycle.viewModelScope
import com.flaringapp.ligretto.android.ui.mvi.MviViewModel
import com.flaringapp.ligretto.android.ui.mvi.dispatch
import com.flaringapp.ligretto.model.Game
import com.flaringapp.ligretto.model.Player
import com.flaringapp.ligretto.usecase.EndLapUseCase
import com.flaringapp.ligretto.usecase.GetCurrentGameWithLapUseCase
import com.flaringapp.ligretto.usecase.GetCurrentLapUseCase
import com.flaringapp.ligretto.usecase.SubmitPlayerLapCardsLeftUseCase
import com.flaringapp.ligretto.usecase.SubmitPlayerLapCardsOnTableUseCase
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@KoinViewModel
class GameLapViewModel(
    private val getCurrentGameWithLapUseCase: GetCurrentGameWithLapUseCase,
    private val getCurrentLapUseCase: GetCurrentLapUseCase,
    private val submitPlayerLapCardsLeftUseCase: SubmitPlayerLapCardsLeftUseCase,
    private val submitPlayerLapCardsOnTableUseCase: SubmitPlayerLapCardsOnTableUseCase,
    private val endLapUseCase: EndLapUseCase,
) : MviViewModel<GameLapState, GameLapIntent, GameLapEffect>(GameLapState()) {

    init {
        dispatch { GameLapIntent.InitDataUpdates }
    }

    override fun reduce(
        state: GameLapState,
        intent: GameLapIntent,
    ): GameLapState = when (intent) {
        GameLapIntent.InitDataUpdates -> initDataUpdates()
        is GameLapIntent.UpdateData -> updateData(intent)
        is GameLapIntent.IncrementCardsLeft -> incrementCardsLeft(intent.player)
        is GameLapIntent.DecrementCardsLeft -> decrementCardsLeft(intent.player)
        is GameLapIntent.IncrementCardsOnTable -> incrementCardsOnTable(intent.player)
        is GameLapIntent.DecrementCardsOnTable -> decrementCardsOnTable(intent.player)
        GameLapIntent.EndLap -> endLap()
        GameLapIntent.HideEndLapConfirmation -> hideEndLapConfirmation()
        GameLapIntent.EndLapConfirmed -> endLapConfirmed()
    }

    private fun initDataUpdates() = state.also {
        viewModelScope.launch {
            getCurrentGameWithLapUseCase().filterNotNull().collect(::handleGameUpdated)
        }
    }

    private fun handleGameUpdated(game: Game) {
        val lap = game.lastLap ?: return
        val playersCards = game.players.map { player ->
            GameLapState.PlayerCards(
                player = player,
                score = game.scores[player]?.value ?: 0,
                cardsLeft = lap.cardsLeft[player] ?: 0,
                cardsOnTable = lap.cardsOnTable[player] ?: 0,
            )
        }

        dispatch {
            GameLapIntent.UpdateData(
                lap = lap,
                playersCards = playersCards,
            )
        }
    }

    private fun updateData(intent: GameLapIntent.UpdateData) = updateState {
        copy(playersCards = intent.playersCards)
    }

    private fun incrementCardsLeft(player: Player) = state.also {
        val lap = getCurrentLapUseCase().value ?: return@also
        val cardsLeft = (lap.cardsLeft[player] ?: 0) + 1
        submitPlayerLapCardsLeftUseCase(player, cardsLeft)
    }

    private fun decrementCardsLeft(player: Player) = state.also {
        val lap = getCurrentLapUseCase().value ?: return@also
        val cardsLeft = (lap.cardsLeft[player] ?: 0) - 1
        submitPlayerLapCardsLeftUseCase(player, cardsLeft)
    }

    private fun incrementCardsOnTable(player: Player) = state.also {
        val lap = getCurrentLapUseCase().value ?: return@also
        val cardsOnTable = (lap.cardsOnTable[player] ?: 0) + 1
        submitPlayerLapCardsOnTableUseCase(player, cardsOnTable)
    }

    private fun decrementCardsOnTable(player: Player) = state.also {
        val lap = getCurrentLapUseCase().value ?: return@also
        val cardsOnTable = (lap.cardsOnTable[player] ?: 0) - 1
        submitPlayerLapCardsOnTableUseCase(player, cardsOnTable)
    }

    private fun endLap() = updateState {
        copy(showConfirmEndLap = true)
    }

    private fun hideEndLapConfirmation() = updateState {
        copy(showConfirmEndLap = false)
    }

    private fun endLapConfirmed() = hideEndLapConfirmation().also {
        endLapUseCase()
        setEffect { GameLapEffect.OpenScores }
    }
}
