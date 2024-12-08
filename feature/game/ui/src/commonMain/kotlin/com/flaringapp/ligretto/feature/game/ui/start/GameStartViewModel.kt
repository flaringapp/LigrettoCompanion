package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
import com.flaringapp.ligretto.core.ui.ext.asUiList
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCachedPreviousGameUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.StartGameUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.StartLapUseCase
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import com.flaringapp.ligretto.feature.game.model.end.GameEndConditions
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@KoinViewModel(binds = [])
internal class GameStartViewModel(
    @InjectedParam restartLastGame: Boolean,
    private val getCachedPreviousGameUseCase: GetCachedPreviousGameUseCase,
    private val startGameUseCase: StartGameUseCase,
    private val startLapUseCase: StartLapUseCase,
) : MviViewModel<GameStartState, GameStartIntent, GameStartEffect>(GameStartState()) {

    private var startGameJob: Job? = null

    init {
        if (restartLastGame) {
            dispatch { GameStartIntent.FetchDataFromLastGame }
        }
    }

    override fun reduce(
        state: GameStartState,
        intent: GameStartIntent,
    ): GameStartState = when (intent) {
        GameStartIntent.FetchDataFromLastGame -> fetchDataFromLastGame()

        is GameStartPlayersIntent -> state.copy(
            players = GameStartPlayersReducer.reduce(
                state = state.players,
                intent = intent,
            ),
        )

        is GameStartEndConditionsIntent -> state.copy(
            endConditions = GameStartEndConditionsReducer.reduce(
                state = state.endConditions,
                intent = intent,
            ),
        )

        GameStartIntent.StartGame -> startGame()
    }

    private fun fetchDataFromLastGame(): GameStartState {
        val game = getCachedPreviousGameUseCase()?.game ?: return state

        return GameStartState(
            players = mapUiGamePlayers(game.players),
            endConditions = mapUiGameEndConditions(game.endConditions),
        )
    }

    private fun mapUiGamePlayers(
        players: List<Player>,
    ): GameStartState.Players {
        val list = players.map { player ->
            GameStartState.Player(
                // TODO fix
                id = player.id.toInt(),
                name = player.name,
            )
        }
        return GameStartState.Players(
            list = list.asUiList(),
            playersIdCounter = (list.maxOfOrNull { it.id } ?: 0) + 1,
        )
    }

    private fun mapUiGameEndConditions(
        endConditions: GameEndConditions,
    ): GameStartState.EndConditions {
        val score = endConditions.score?.let {
            GameEndConditionScoreLimitState(
                isEnabled = true,
                selectedScore = it.targetScore.value,
            )
        } ?: GameEndConditionScoreLimitState()

        val time = endConditions.time?.let {
            GameEndConditionTimeLimitState(
                isEnabled = true,
                selectedMinutes = it.gameDuration.inWholeMinutes.toInt(),
            )
        } ?: GameEndConditionTimeLimitState()

        return GameStartState.EndConditions(
            score = score,
            time = time,
        )
    }

    private fun startGame() = state.also {
        if (startGameJob?.isActive == true) return@also

        val hasEmptyNames = state.players.list.any { it.name.isBlank() }
        if (hasEmptyNames) return@also

        val config = createGameConfig()

        // TODO loading/disable button?
        // TODO error handling
        startGameJob = viewModelScope.launch {
            startGameUseCase(config)
            startLapUseCase()

            setEffect { GameStartEffect.StartGame }
        }

        return@also
    }

    private fun createGameConfig(): GameConfig {
        val players = state.players.list.map { player ->
            Player(
                id = player.id.toLong(),
                name = player.name,
            )
        }

        return GameConfig(
            players = players,
            targetScore = resolveEndConditionScore(),
            timeLimit = resolveEndConditionTime(),
        )
    }

    private fun resolveEndConditionScore(): Score? {
        val condition = state.endConditions.score
        if (!condition.isEnabled) return null

        return Score(condition.selectedScore)
    }

    private fun resolveEndConditionTime(): Duration? {
        val condition = state.endConditions.time
        if (!condition.isEnabled) return null

        return condition.selectedMinutes.minutes
    }
}
