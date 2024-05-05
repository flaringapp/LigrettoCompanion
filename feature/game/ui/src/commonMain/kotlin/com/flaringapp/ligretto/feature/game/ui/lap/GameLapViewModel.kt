package com.flaringapp.ligretto.feature.game.ui.lap

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
import com.flaringapp.ligretto.core.ui.ext.asUiList
import com.flaringapp.ligretto.feature.game.domain.usecase.EndLapUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentGameUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentGameWithLapUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentLapUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.SubmitPlayerLapCardsLeftUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.SubmitPlayerLapCardsOnTableUseCase
import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.Player
import org.koin.core.annotation.Factory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Factory
internal class GameLapViewModel(
    private val getCurrentGameWithLapUseCase: GetCurrentGameWithLapUseCase,
    private val getCurrentGameUseCase: GetCurrentGameUseCase,
    private val getCurrentLapUseCase: GetCurrentLapUseCase,
    private val submitPlayerLapCardsLeftUseCase: SubmitPlayerLapCardsLeftUseCase,
    private val submitPlayerLapCardsOnTableUseCase: SubmitPlayerLapCardsOnTableUseCase,
    private val endLapUseCase: EndLapUseCase,
) : MviViewModel<GameLapState, GameLapIntent, GameLapEffect>(GameLapState()) {

    private var endLapJob: Job? = null

    init {
        dispatch { GameLapIntent.InitDataUpdates }
    }

    override fun reduce(
        state: GameLapState,
        intent: GameLapIntent,
    ): GameLapState = when (intent) {
        GameLapIntent.InitDataUpdates -> initDataUpdates()
        is GameLapIntent.UpdateData -> updateData(intent)
        is GameLapIntent.IncrementCardsLeft -> incrementCardsLeft(intent.playerId)
        is GameLapIntent.DecrementCardsLeft -> decrementCardsLeft(intent.playerId)
        is GameLapIntent.IncrementCardsOnTable -> incrementCardsOnTable(intent.playerId)
        is GameLapIntent.DecrementCardsOnTable -> decrementCardsOnTable(intent.playerId)
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
            val uiPlayer = GameLapState.Player(
                id = player.id,
                name = player.name,
            )
            GameLapState.PlayerCards(
                player = uiPlayer,
                score = game.scores[player]?.value ?: 0,
                cardsLeft = lap.cardsLeft[player] ?: 0,
                cardsOnTable = lap.cardsOnTable[player] ?: 0,
            )
        }

        dispatch {
            GameLapIntent.UpdateData(
                playersCards = playersCards.asUiList(),
            )
        }
    }

    private fun updateData(intent: GameLapIntent.UpdateData) = updateState {
        copy(playersCards = intent.playersCards)
    }

    private fun incrementCardsLeft(playerId: Long) = state.also {
        val player = findPlayer(playerId) ?: return@also
        val lap = getCurrentLapUseCase().value ?: return@also
        val cardsLeft = (lap.cardsLeft[player] ?: 0) + 1

        // TODO Synchronize?
        viewModelScope.launch {
            submitPlayerLapCardsLeftUseCase(player, cardsLeft)
        }
    }

    private fun decrementCardsLeft(playerId: Long) = state.also {
        val player = findPlayer(playerId) ?: return@also
        val lap = getCurrentLapUseCase().value ?: return@also
        val cardsLeft = (lap.cardsLeft[player] ?: 0) - 1

        // TODO Synchronize?
        viewModelScope.launch {
            submitPlayerLapCardsLeftUseCase(player, cardsLeft)
        }
    }

    private fun incrementCardsOnTable(playerId: Long) = state.also {
        val player = findPlayer(playerId) ?: return@also
        val lap = getCurrentLapUseCase().value ?: return@also
        val cardsOnTable = (lap.cardsOnTable[player] ?: 0) + 1

        // TODO Synchronize?
        viewModelScope.launch {
            submitPlayerLapCardsOnTableUseCase(player, cardsOnTable)
        }
    }

    private fun decrementCardsOnTable(playerId: Long) = state.also {
        val player = findPlayer(playerId) ?: return@also
        val lap = getCurrentLapUseCase().value ?: return@also
        val cardsOnTable = (lap.cardsOnTable[player] ?: 0) - 1

        // TODO Synchronize?
        viewModelScope.launch {
            submitPlayerLapCardsOnTableUseCase(player, cardsOnTable)
        }
    }

    private fun endLap() = updateState {
        if (endLapJob?.isActive == true) return@updateState this

        copy(showConfirmEndLap = true)
    }

    private fun hideEndLapConfirmation() = updateState {
        copy(showConfirmEndLap = false)
    }

    private fun endLapConfirmed() = hideEndLapConfirmation().also {
        if (endLapJob?.isActive == true) return@also

        // TODO loading/disable button?
        // TODO error handling
        viewModelScope.launch(Dispatchers.IO) {
            val game = endLapUseCase()
            if (game?.matchesEndConditions == true) {
                setEffect { GameLapEffect.EndGame }
                return@launch
            }

            setEffect { GameLapEffect.OpenScores }
        }
    }

    private fun findPlayer(id: Long): Player? {
        val game = getCurrentGameUseCase().value ?: return null
        return game.players.find { it.id == id }
    }
}
