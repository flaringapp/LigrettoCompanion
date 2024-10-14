package com.flaringapp.ligretto.feature.game.ui.close

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.UiEffect
import com.flaringapp.ligretto.core.arch.UiIntent
import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentGameUseCase
import org.koin.android.annotation.KoinViewModel

internal object GameCloseState : UiState

internal sealed interface GameCloseIntent : UiIntent {
    object Approve : GameCloseIntent
    object Dismiss : GameCloseIntent
}

internal sealed interface GameCloseEffect : UiEffect {
    object EndGame : GameCloseEffect
    object CloseGame : GameCloseEffect
    object Dismiss : GameCloseEffect
}

@KoinViewModel
internal class GameCloseViewModel(
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
