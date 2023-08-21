package com.flaringapp.ligretto.feature.home.ui.home

import androidx.lifecycle.viewModelScope
import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
import com.flaringapp.ligretto.feature.home.domain.usecase.ResumePreviousGameUseCase
import com.flaringapp.ligretto.feature.home.domain.usecase.GetHomeDataUseCase
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.launch

@KoinViewModel
internal class HomeViewModel(
    private val getHomeDataUseCase: GetHomeDataUseCase,
    private val resumePreviousGameUseCase: ResumePreviousGameUseCase,
) : MviViewModel<HomeState, HomeIntent, HomeEffect>(HomeState()) {

    init {
        viewModelScope.launch {
            getHomeDataUseCase().collect { data ->
                val state = HomeState(
                    hasActiveGame = data.activeGame != null,
                    hasPreviousGame = data.previousGame != null,
                )

                dispatch { HomeIntent.UpdateData(state) }
            }
        }
    }

    override fun reduce(
        state: HomeState,
        intent: HomeIntent,
    ): HomeState = when (intent) {
        is HomeIntent.UpdateData -> intent.state
        HomeIntent.StartNewGame -> startNewGame()
        HomeIntent.RestartLastGame -> restartLastGame()
        HomeIntent.ContinueActiveGame -> continueActiveGame()
    }

    private fun startNewGame(): HomeState = state.also {
        setEffect { HomeEffect.OpenStartGame(restartLastGame = false) }
    }

    private fun restartLastGame(): HomeState = state.also {
        setEffect { HomeEffect.OpenStartGame(restartLastGame = true) }
    }

    private fun continueActiveGame(): HomeState = state.also {
        viewModelScope.launch {
            val game = resumePreviousGameUseCase() ?: run {
                // TODO check reason
                setEffect { HomeEffect.OpenActiveGameEnded }
                return@launch
            }

            val openLap = game.activeLap != null

            setEffect { HomeEffect.OpenResumeGame(openLap = openLap) }
        }
    }
}
