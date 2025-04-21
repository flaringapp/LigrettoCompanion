package com.flaringapp.ligretto.feature.game.ui.lap.start

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentLapUseCase
import com.flaringapp.ligretto.feature.game.model.Lap
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@KoinViewModel
internal class GameLapStartViewModel(
    private val getCurrentLapUseCase: GetCurrentLapUseCase,
) : MviViewModel<GameLapStartState, GameLapStartIntent, GameLapStartEffect>(
    GameLapStartState(),
) {

    init {
        dispatch(GameLapStartIntent.Init)
    }

    override fun reduce(
        state: GameLapStartState,
        intent: GameLapStartIntent,
    ): GameLapStartState = when (intent) {
        GameLapStartIntent.Init -> {
            init()
        }

        is GameLapStartIntent.UpdateData -> intent.state

        GameLapStartIntent.StartLap -> {
            startLap()
        }
    }

    private fun init() = state.also {
        viewModelScope.launch {
            getCurrentLapUseCase()
                .filterNotNull()
                .collect(::handleLapUpdated)
        }
    }

    private fun handleLapUpdated(lap: Lap) {
        val state = GameLapStartState(
            lapNumber = lap.number,
        )

        dispatch { GameLapStartIntent.UpdateData(state) }
    }

    private fun startLap() = state.also {
        setEffect { GameLapStartEffect.OpenLap }
    }
}
