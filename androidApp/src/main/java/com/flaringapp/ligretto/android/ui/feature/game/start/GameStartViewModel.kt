package com.flaringapp.ligretto.android.ui.feature.game.start

import com.flaringapp.ligretto.android.ui.mvi.MviViewModel
import com.flaringapp.ligretto.model.GameConfig
import com.flaringapp.ligretto.model.Player
import com.flaringapp.ligretto.usecase.StartGameUseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class GameStartViewModel(
    private val startGameUseCase: StartGameUseCase,
) : MviViewModel<GameStartState, GameStartIntent, GameStartEffect>(GameStartState()) {

    override fun reduce(
        state: GameStartState,
        intent: GameStartIntent,
    ): GameStartState = when (intent) {
        GameStartPlayersIntent.AddNew -> addNewPlayer()
        is GameStartPlayersIntent.ChangeName -> changePlayerName(intent.id, intent.name)
        is GameStartPlayersIntent.FocusChanged -> handlePlayerFocusChanged(
            id = intent.id,
            isFocused = intent.isFocused,
        )
        is GameStartPlayersIntent.Remove -> removePlayer(intent.id)
        GameStartIntent.StartGame -> startGame()
    }

    private fun addNewPlayer() = updatePlayersState {
        val id = playersIdCounter + 1
        val newPlayers = list + GameStartState.Player(id, "")
        copy(
            list = newPlayers,
            playersIdCounter = id,
            focusedPlayerId = id,
        )
    }

    private fun changePlayerName(id: Int, name: String) = updatePlayersState {
        val newPlayers = list.map { player ->
            if (player.id != id) return@map player
            player.copy(name = name)
        }
        copy(list = newPlayers)
    }

    private fun handlePlayerFocusChanged(id: Int, isFocused: Boolean) = updatePlayersState {
        val newFocusedPlayerId = run {
            if (isFocused) return@run id
            focusedPlayerId.takeIf { it != id }
        }
        copy(focusedPlayerId = newFocusedPlayerId)
    }

    private fun removePlayer(id: Int) = updatePlayersState {
        val newPlayers = list.filterNot { it.id == id }
        copy(list = newPlayers)
    }

    private fun startGame() = state.also {
        val hasEmptyNames = state.players.list.any { it.name.isBlank() }
        if (hasEmptyNames) return@also

        val config = createGameConfig()
        startGameUseCase(config)

        setEffect { GameStartEffect.StartGame }
        return@also
    }

    private fun createGameConfig(): GameConfig {
        val players = state.players.list.map { player ->
            Player(
                id = player.id,
                name = player.name,
            )
        }

        return GameConfig(
            players = players,
        )
    }

    private inline fun updatePlayersState(
        build: GameStartState.Players.() -> GameStartState.Players,
    ): GameStartState = updateState {
        copy(players = players.build())
    }
}
