package com.flaringapp.ligretto.feature.home.ui

import androidx.lifecycle.viewModelScope
import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
import com.flaringapp.ligretto.feature.game.domain.usecase.GetPreviousGameUseCase
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.launch

@KoinViewModel
internal class HomeViewModel(
    private val getPreviousGameUseCase: GetPreviousGameUseCase,
) : MviViewModel<HomeState, HomeIntent, HomeEffect>(HomeState()) {

    init {
        viewModelScope.launch {
            getPreviousGameUseCase().collect {
                val hasPreviousGame = it != null
                dispatch { HomeIntent.UpdateHasPreviousGame(hasPreviousGame) }
            }
        }
    }

    override fun reduce(
        state: HomeState,
        intent: HomeIntent,
    ): HomeState = when (intent) {
        is HomeIntent.UpdateHasPreviousGame -> updateHasPreviousGame(intent.hasPreviousGame)
        HomeIntent.StartNewGame -> startNewGame()
        HomeIntent.RestartLastGame -> restartLastGame()
    }

    private fun updateHasPreviousGame(hasPreviousGame: Boolean) = updateState {
        copy(hasPreviousGame = hasPreviousGame)
    }

    private fun startNewGame(): HomeState = state.also {
        setEffect { HomeEffect.OpenStartGame(restartLastGame = false) }
    }

    private fun restartLastGame(): HomeState = state.also {
        setEffect { HomeEffect.OpenStartGame(restartLastGame = true) }
    }
}
