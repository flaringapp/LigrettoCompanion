package com.flaringapp.ligretto.feature.game.ui.score

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
import com.flaringapp.ligretto.core.ui.ext.asUiList
import com.flaringapp.ligretto.core.util.common.isRunning
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentGameUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.StartLapUseCase
import com.flaringapp.ligretto.feature.game.model.Game
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@KoinViewModel(binds = [])
internal class GameScoreViewModel(
    private val getCurrentGameUseCase: GetCurrentGameUseCase,
    private val startLapUseCase: StartLapUseCase,
) : MviViewModel<GameScoreState, GameScoreIntent, GameScoreEffect>(GameScoreState()) {

    private var startLapJob: Job? = null

    init {
        dispatch { GameScoreIntent.Init }
    }

    override fun reduce(
        state: GameScoreState,
        intent: GameScoreIntent,
    ): GameScoreState = when (intent) {
        GameScoreIntent.Init -> init()
        is GameScoreIntent.UpdateData -> intent.state
        GameScoreIntent.StartNextLap -> startNewLap()
    }

    private fun init() = state.also {
        viewModelScope.launch {
            getCurrentGameUseCase()
                .filterNotNull()
                .collect(::handleGameUpdated)
        }
    }

    private fun handleGameUpdated(game: Game) {
        val playerScores = mapPlayerScores(game)
        val endConditions = mapEndConditions(game)

        dispatch {
            GameScoreIntent.UpdateData(
                GameScoreState(
                    nextRoundNumber = game.pendingLapNumber,
                    playerScores = playerScores.asUiList(),
                    endConditions = endConditions,
                ),
            )
        }
    }

    private fun mapPlayerScores(game: Game): List<GameScoreState.PlayerScore> {
        return game.players
            .sortedByDescending { game.scores[it] }
            .mapIndexed { place, player ->
                GameScoreState.PlayerScore(
                    place = place + 1,
                    playerName = player.name,
                    score = game.scores[player]?.value ?: 0,
                )
            }
    }

    private fun mapEndConditions(game: Game): GameScoreState.EndConditions? {
        if (game.endConditions.isEmpty) return null

        val score = game.endConditions.score?.targetScore?.value
        val time = game.endConditions.time?.let {
            GameScoreState.EndConditions.Time(
                timeEnd = game.timeStarted + it.gameDuration,
                clock = it.clock,
            )
        }

        return GameScoreState.EndConditions(
            score = score,
            time = time,
        )
    }

    private fun startNewLap(): GameScoreState = state.also {
        if (startLapJob.isRunning) return@also

        val game = getCurrentGameUseCase().value
        if (game?.matchesEndConditions == true) {
            setEffect { GameScoreEffect.EndGame }
            return@also
        }

        // TODO loading/disable button?
        // TODO error handling
        startLapJob = viewModelScope.launch {
            startLapUseCase()
            setEffect { GameScoreEffect.OpenNextLap }
        }
    }
}
