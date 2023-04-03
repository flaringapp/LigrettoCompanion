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
        GameStartIntent.AddNewPlayer -> addPlayer()
        is GameStartIntent.ChangePlayerName -> changePlayerName(intent.id, intent.name)
        is GameStartIntent.PlayerFocusChanged -> handlePlayerFocusChanged(
            id = intent.id,
            isFocused = intent.isFocused,
        )
        is GameStartIntent.RemovePlayer -> removePlayer(intent.id)
        GameStartIntent.StartGame -> startGame()
    }

    private fun addPlayer() = updateState {
        val id = playersIdCounter + 1
        val newPlayers = players + GameStartState.Player(id, "")
        copy(
            isEmpty = false,
            players = newPlayers,
            playersIdCounter = id,
            focusedPlayerId = id,
        )
    }

    private fun changePlayerName(id: Int, name: String) = updateState {
        val newPlayers = players.map { player ->
            if (player.id != id) return@map player
            player.copy(name = name)
        }
        copy(players = newPlayers)
    }

    private fun handlePlayerFocusChanged(id: Int, isFocused: Boolean) = updateState {
        val newFocusedPlayerId = run {
            if (isFocused) return@run id
            focusedPlayerId.takeIf { it != id }
        }
        copy(focusedPlayerId = newFocusedPlayerId)
    }

    private fun removePlayer(id: Int) = updateState {
        val newPlayers = players.filterNot { it.id == id }
        copy(
            isEmpty = newPlayers.isEmpty(),
            players = newPlayers,
        )
    }

    private fun startGame() = state.also {
        val hasEmptyNames = state.players.any { it.name.isBlank() }
        if (hasEmptyNames) return@also

        val config = createGameConfig()
        startGameUseCase(config)

        setEffect { GameStartEffect.StartGame }
        return@also
    }

    private fun createGameConfig(): GameConfig {
        val players = state.players.map { player ->
            Player(
                id = player.id,
                name = player.name,
            )
        }

        return GameConfig(players)
    }
}
