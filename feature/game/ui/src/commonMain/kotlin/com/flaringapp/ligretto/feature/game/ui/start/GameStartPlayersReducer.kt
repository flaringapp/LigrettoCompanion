package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.core.arch.Reducer
import com.flaringapp.ligretto.core.ui.ext.asUiList
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.Players

internal object GameStartPlayersReducer : Reducer<Players, GameStartPlayersIntent> {

    override fun reduce(
        state: Players,
        intent: GameStartPlayersIntent,
    ): Players = when (intent) {
        GameStartPlayersIntent.AddNew -> {
            state.addNewPlayer()
        }

        is GameStartPlayersIntent.ChangeName -> {
            state.changePlayerName(
                id = intent.id,
                name = intent.name
            )
        }

        is GameStartPlayersIntent.FocusChanged -> {
            state.handlePlayerFocusChanged(
                id = intent.id,
                isFocused = intent.isFocused,
            )
        }

        is GameStartPlayersIntent.Remove -> {
            state.removePlayer(
                id = intent.id,
            )
        }
    }

    private fun Players.addNewPlayer(): Players {
        val id = playersIdCounter + 1
        val newList = list + GameStartState.Player(id, "")
        return copy(
            list = newList.asUiList(),
            playersIdCounter = id,
            focusedPlayerId = id,
        )
    }

    private fun Players.changePlayerName(id: Int, name: String): Players {
        val newList = list.map { player ->
            if (player.id != id) return@map player
            player.copy(name = name)
        }
        return copy(list = newList.asUiList())
    }

    private fun Players.handlePlayerFocusChanged(id: Int, isFocused: Boolean): Players {
        val newFocusedPlayerId = run {
            if (isFocused) return@run id
            focusedPlayerId.takeIf { it != id }
        }
        return copy(focusedPlayerId = newFocusedPlayerId)
    }

    private fun Players.removePlayer(id: Int): Players {
        val newList = list.filterNot { it.id == id }
        return copy(list = newList.asUiList())
    }
}
