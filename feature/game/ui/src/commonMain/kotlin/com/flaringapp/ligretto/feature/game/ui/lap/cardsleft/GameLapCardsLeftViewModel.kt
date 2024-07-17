package com.flaringapp.ligretto.feature.game.ui.lap.cardsleft

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
import com.flaringapp.ligretto.core.ui.ext.asUiList
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentGameUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentGameWithLapUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentLapUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.SubmitPlayerLapCardsLeftUseCase
import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.Player
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsState
import org.koin.core.annotation.Factory
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Factory
internal class GameLapCardsLeftViewModel(
    private val getCurrentGameWithLapUseCase: GetCurrentGameWithLapUseCase,
    private val getCurrentGameUseCase: GetCurrentGameUseCase,
    private val getCurrentLapUseCase: GetCurrentLapUseCase,
    private val submitPlayerLapCardsLeftUseCase: SubmitPlayerLapCardsLeftUseCase,
) : MviViewModel<GameLapCardsLeftState, GameLapCardsLeftIntent, GameLapCardsLeftEffect>(
    GameLapCardsLeftState(
        roundNumber = getCurrentLapUseCase().value?.number ?: 0,
    )
) {

    init {
        dispatch { GameLapCardsLeftIntent.Init }
    }

    override fun reduce(
        state: GameLapCardsLeftState,
        intent: GameLapCardsLeftIntent,
    ): GameLapCardsLeftState = when (intent) {
        GameLapCardsLeftIntent.Init -> init()
        is GameLapCardsLeftIntent.UpdatePlayerCards -> updatePlayerCards(intent)
        is GameLapCardsLeftIntent.IncrementCards -> incrementCards(intent.playerId)
        is GameLapCardsLeftIntent.DecrementCards -> decrementCards(intent.playerId)
        GameLapCardsLeftIntent.OpenCardsOnTable -> openCardsOnTable()
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
                cardsCount = lap.cardsLeft[player] ?: 0,
            )
        }

        dispatch {
            GameLapCardsLeftIntent.UpdatePlayerCards(
                playerCards = playersCards.asUiList(),
            )
        }
    }

    private fun updatePlayerCards(
        intent: GameLapCardsLeftIntent.UpdatePlayerCards
    ) = updateState {
        copy(
            playerCards = intent.playerCards,
        )
    }

    private fun incrementCards(
        playerId: Long,
    ) = changeCardsLeft(
        playerId = playerId,
        delta = 1,
    )

    private fun decrementCards(
        playerId: Long,
    ) = changeCardsLeft(
        playerId = playerId,
        delta = -1,
    )

    private fun changeCardsLeft(
        playerId: Long,
        delta: Int,
    ) = state.also {
        val player = findPlayer(playerId) ?: return@also
        val lap = getCurrentLapUseCase().value ?: return@also
        val cardsLeft = ((lap.cardsLeft[player] ?: 0) + delta).coerceAtLeast(0)

        // TODO Synchronize?
        viewModelScope.launch {
            submitPlayerLapCardsLeftUseCase(
                player = player,
                count = cardsLeft,
            )
        }
    }

    private fun openCardsOnTable() = state.also {
        setEffect { GameLapCardsLeftEffect.OpenCardsOnTable }
    }

    private fun findPlayer(id: Long): Player? {
        val game = getCurrentGameUseCase().value ?: return null
        return game.players.find { it.id == id }
    }
}
