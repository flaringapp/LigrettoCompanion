package com.flaringapp.ligretto.feature.game.ui.lap.cardsontable

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
import com.flaringapp.ligretto.core.ui.ext.asUiList
import com.flaringapp.ligretto.feature.game.domain.usecase.EndLapUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentGameUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentGameWithLapUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentLapUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.SubmitPlayerLapCardsOnTableUseCase
import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.Player
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsState
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@KoinViewModel(binds = [])
internal class GameLapCardsOnTableViewModel(
    private val getCurrentGameWithLapUseCase: GetCurrentGameWithLapUseCase,
    private val getCurrentGameUseCase: GetCurrentGameUseCase,
    private val getCurrentLapUseCase: GetCurrentLapUseCase,
    private val submitPlayerLapCardsOnTableUseCase: SubmitPlayerLapCardsOnTableUseCase,
    private val endLapUseCase: EndLapUseCase,
) : MviViewModel<GameLapCardsOnTableState, GameLapCardsOnTableIntent, GameLapCardsOnTableEffect>(
    GameLapCardsOnTableState(
        roundNumber = getCurrentLapUseCase().value?.number ?: 0,
    ),
) {

    private var endLapJob: Job? = null

    init {
        dispatch { GameLapCardsOnTableIntent.Init }
    }

    override fun reduce(
        state: GameLapCardsOnTableState,
        intent: GameLapCardsOnTableIntent,
    ): GameLapCardsOnTableState = when (intent) {
        GameLapCardsOnTableIntent.Init -> init()
        is GameLapCardsOnTableIntent.UpdatePlayerCards -> updatePlayerCards(intent)
        is GameLapCardsOnTableIntent.IncrementCards -> incrementCards(intent.playerId)
        is GameLapCardsOnTableIntent.DecrementCards -> decrementCards(intent.playerId)
        GameLapCardsOnTableIntent.EndLap -> endLap()
        GameLapCardsOnTableIntent.HideEndLapConfirmation -> hideEndLapConfirmation()
        GameLapCardsOnTableIntent.EndLapConfirmed -> endLapConfirmed()
    }

    private fun init() = state.also {
        viewModelScope.launch {
            getCurrentGameWithLapUseCase()
                .filterNotNull()
                .collect(::handleGameUpdated)
        }
    }

    private fun handleGameUpdated(game: Game) {
        val lap = game.lastLap ?: return
        val playersCards = game.players.map { player ->
            GameLapPlayerCardsState(
                playerId = player.id,
                playerName = player.name,
                totalScore = game.scores[player]?.value ?: 0,
                cardsCount = lap.cardsOnTable[player] ?: 0,
            )
        }

        dispatch {
            GameLapCardsOnTableIntent.UpdatePlayerCards(
                playerCards = playersCards.asUiList(),
            )
        }
    }

    private fun updatePlayerCards(
        intent: GameLapCardsOnTableIntent.UpdatePlayerCards,
    ) = updateState {
        copy(
            playerCards = intent.playerCards,
        )
    }

    private fun incrementCards(
        playerId: Long,
    ) = changeCardsOnTable(
        playerId = playerId,
        delta = 1,
    )

    private fun decrementCards(
        playerId: Long,
    ) = changeCardsOnTable(
        playerId = playerId,
        delta = -1,
    )

    private fun changeCardsOnTable(playerId: Long, delta: Int) = state.also {
        val player = findPlayer(playerId) ?: return@also
        val lap = getCurrentLapUseCase().value ?: return@also
        val cardsOnTable = ((lap.cardsOnTable[player] ?: 0) + delta).coerceAtLeast(0)

        // TODO Synchronize?
        viewModelScope.launch {
            submitPlayerLapCardsOnTableUseCase(
                player = player,
                count = cardsOnTable,
            )
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
        endLapJob = viewModelScope.launch(Dispatchers.IO) {
            val game = endLapUseCase()
            if (game?.matchesEndConditions == true) {
                setEffect { GameLapCardsOnTableEffect.EndGame }
                return@launch
            }

            setEffect { GameLapCardsOnTableEffect.OpenScores }
        }
    }

    private fun findPlayer(id: Long): Player? {
        val game = getCurrentGameUseCase().value ?: return null
        return game.players.find { it.id == id }
    }
}
