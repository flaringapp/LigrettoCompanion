package com.flaringapp.ligretto.android.ui.feature.game.lap

import androidx.lifecycle.viewModelScope
import com.flaringapp.ligretto.android.ui.mvi.MviViewModel
import com.flaringapp.ligretto.android.ui.mvi.dispatch
import com.flaringapp.ligretto.model.Player
import com.flaringapp.ligretto.usecase.EndLapUseCase
import com.flaringapp.ligretto.usecase.GetCurrentGameUseCase
import com.flaringapp.ligretto.usecase.GetCurrentLapUseCase
import com.flaringapp.ligretto.usecase.SubmitPlayerLapCardsLeftUseCase
import com.flaringapp.ligretto.usecase.SubmitPlayerLapCardsOnTableUseCase
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@KoinViewModel
class GameLapViewModel(
    private val getCurrentGameUseCase: GetCurrentGameUseCase,
    private val getCurrentLapUseCase: GetCurrentLapUseCase,
    private val submitPlayerLapCardsLeftUseCase: SubmitPlayerLapCardsLeftUseCase,
    private val submitPlayerLapCardsOnTableUseCase: SubmitPlayerLapCardsOnTableUseCase,
    private val endLapUseCase: EndLapUseCase,
) : MviViewModel<GameLapState, GameLapIntent, GameLapEffect>(GameLapState()) {

    init {
        loadData()
    }

    override fun reduce(state: GameLapState, intent: GameLapIntent): GameLapState {
        return when (intent) {
            is GameLapIntent.UpdateData -> updateData(intent)
            is GameLapIntent.IncrementCardsLeft -> incrementCardsLeft(intent.player)
            is GameLapIntent.DecrementCardsLeft -> decrementCardsLeft(intent.player)
            is GameLapIntent.IncrementCardsOnTable -> incrementCardsOnTable(intent.player)
            is GameLapIntent.DecrementCardsOnTable -> decrementCardsOnTable(intent.player)
            GameLapIntent.EndLap -> endLap()
            GameLapIntent.HideEndLapConfirmation -> hideEndLapConfirmation()
            GameLapIntent.EndLapConfirmed -> endLapConfirmed()
        }
    }

    private fun loadData() {
        val game = getCurrentGameUseCase().value ?: return

        viewModelScope.launch {
            getCurrentLapUseCase().filterNotNull().collect { lap ->
                val playersCards = game.players.map { player ->
                    GameLapState.PlayerCards(
                        player = player,
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
