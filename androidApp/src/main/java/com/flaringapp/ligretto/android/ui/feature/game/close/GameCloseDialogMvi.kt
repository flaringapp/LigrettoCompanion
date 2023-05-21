package com.flaringapp.ligretto.android.ui.feature.game.close

import com.flaringapp.ligretto.android.ui.mvi.MviViewModel
import com.flaringapp.ligretto.android.ui.mvi.UiEffect
import com.flaringapp.ligretto.android.ui.mvi.UiIntent
import com.flaringapp.ligretto.android.ui.mvi.UiState
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentGameUseCase
import org.koin.android.annotation.KoinViewModel

object GameCloseState : UiState

sealed interface GameCloseIntent : UiIntent {
    object Approve : GameCloseIntent
    object Dismiss : GameCloseIntent
}

sealed interface GameCloseEffect : UiEffect {
    object EndGame : GameCloseEffect
    object CloseGame : GameCloseEffect
    object Dismiss : GameCloseEffect
}

@KoinViewModel
class GameCloseViewModel(
    private val getCurrentGameUseCase: GetCurrentGameUseCase,
) : MviViewModel<GameCloseState, GameCloseIntent, GameCloseEffect>(GameCloseState) {

    override fun reduce(
        state: GameCloseState,
        intent: GameCloseIntent,
    ): GameCloseState = when (intent) {
        GameCloseIntent.Approve -> approve()
        GameCloseIntent.Dismiss -> dismiss()
    }

    private fun approve() = state.also {
        val hasActiveGame = getCurrentGameUseCase().value != null
        setEffect {
            if (hasActiveGame) GameCloseEffect.EndGame else GameCloseEffect.CloseGame
        }
    }

    private fun dismiss() = state.also {
        setEffect { GameCloseEffect.Dismiss }
    }
}
