package com.flaringapp.ligretto.android.ui.feature.game.close

import com.flaringapp.ligretto.android.ui.mvi.MviViewModel
import com.flaringapp.ligretto.android.ui.mvi.UiEffect
import com.flaringapp.ligretto.android.ui.mvi.UiIntent
import com.flaringapp.ligretto.android.ui.mvi.UiState
import org.koin.android.annotation.KoinViewModel

object GameCloseState : UiState

sealed interface GameCloseIntent : UiIntent {
    object Approve : GameCloseIntent
    object Dismiss : GameCloseIntent
}

sealed interface GameCloseEffect : UiEffect {
    object EndGame : GameCloseEffect
    object Close : GameCloseEffect
}

@KoinViewModel
class GameCloseViewModel :
    MviViewModel<GameCloseState, GameCloseIntent, GameCloseEffect>(GameCloseState) {

    override fun reduce(state: GameCloseState, intent: GameCloseIntent): GameCloseState {
        return when (intent) {
            GameCloseIntent.Approve -> approve()
            GameCloseIntent.Dismiss -> dismiss()
        }
    }

    private fun approve() = state.also {
        setEffect { GameCloseEffect.EndGame }
    }

    private fun dismiss() = state.also {
        setEffect { GameCloseEffect.Close }
    }
}
