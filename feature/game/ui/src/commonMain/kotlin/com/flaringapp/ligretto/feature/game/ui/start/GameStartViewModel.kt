package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
import com.flaringapp.ligretto.core.ui.ext.asUiList
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCachedPreviousGameUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.StartGameUseCase
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import com.flaringapp.ligretto.feature.game.model.end.GameEndConditions
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private typealias ScoreEndConditionState = GameStartState.EndConditions.ScoreLimit
private typealias TimeEndConditionState = GameStartState.EndConditions.TimeLimit

@KoinViewModel
internal class GameStartViewModel(
    @InjectedParam restartLastGame: Boolean,
    private val getCachedPreviousGameUseCase: GetCachedPreviousGameUseCase,
    private val startGameUseCase: StartGameUseCase,
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
        //region Players
        GameStartPlayersIntent.AddNew -> addNewPlayer()
        is GameStartPlayersIntent.ChangeName -> changePlayerName(intent.id, intent.name)
        is GameStartPlayersIntent.FocusChanged -> handlePlayerFocusChanged(
            id = intent.id,
            isFocused = intent.isFocused,
        )

        is GameStartPlayersIntent.Remove -> removePlayer(intent.id)
        //endregion
        //region End Conditions
        is GameStartScoreEndConditionIntent.SetEnabled -> {
            setScoreEndConditionEnabled(intent.isEnabled)
        }

        is GameStartScoreEndConditionIntent.ValueChange -> {
            changeScoreEndConditionValue(intent.value)
        }

        is GameStartTimeEndConditionIntent.SetEnabled -> {
            setTimeEndConditionEnabled(intent.isEnabled)
        }

        is GameStartTimeEndConditionIntent.HourChange -> {
            changeTimeEndConditionHour(intent.value)
        }

        is GameStartTimeEndConditionIntent.MinuteChange -> {
            changeTimeEndConditionMinute(intent.value)
        }
        //endregion
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
            GameStartState.EndConditions.ScoreLimit(
                isEnabled = true,
                value = it.targetScore.value.toString(),
            )
        } ?: GameStartState.EndConditions.ScoreLimit()

        val time = endConditions.time?.let {
            val hours = it.gameDuration.inWholeHours.hours
            val minutes = (it.gameDuration - hours).inWholeMinutes.minutes
            GameStartState.EndConditions.TimeLimit(
                isEnabled = true,
                hours = hours.inWholeHours.toString(),
                minutes = minutes.inWholeMinutes.toString(),
            )
        } ?: GameStartState.EndConditions.TimeLimit()

        return GameStartState.EndConditions(
            score = score,
            time = time,
        )
    }

    private fun addNewPlayer() = updatePlayersState {
        val id = playersIdCounter + 1
        val newList = list + GameStartState.Player(id, "")
        copy(
            list = newList.asUiList(),
            playersIdCounter = id,
            focusedPlayerId = id,
        )
    }

    private fun changePlayerName(id: Int, name: String) = updatePlayersState {
        val newList = list.map { player ->
            if (player.id != id) return@map player
            player.copy(name = name)
        }
        copy(list = newList.asUiList())
    }

    private fun handlePlayerFocusChanged(id: Int, isFocused: Boolean) = updatePlayersState {
        val newFocusedPlayerId = run {
            if (isFocused) return@run id
            focusedPlayerId.takeIf { it != id }
        }
        copy(focusedPlayerId = newFocusedPlayerId)
    }

    private fun removePlayer(id: Int) = updatePlayersState {
        val newList = list.filterNot { it.id == id }
        copy(list = newList.asUiList())
    }

    private fun setScoreEndConditionEnabled(isEnabled: Boolean) = updateScoreEndConditionState {
        copy(isEnabled = isEnabled)
    }

    private fun changeScoreEndConditionValue(input: String) = updateScoreEndConditionState {
        copy(
            value = input.filterDigits(maxLength = 3),
        )
    }

    private fun setTimeEndConditionEnabled(isEnabled: Boolean) = updateTimeEndConditionState {
        copy(isEnabled = isEnabled)
    }

    private fun changeTimeEndConditionHour(input: String) = updateTimeEndConditionState {
        copy(
            hours = input.filterDigits(maxLength = 2),
        )
    }

    private fun changeTimeEndConditionMinute(input: String) = updateTimeEndConditionState {
        copy(
            minutes = input.filterDigits(maxLength = 2),
        )
    }

    private fun startGame() = state.also {
        if (startGameJob?.isActive == true) return@also

        val hasEmptyNames = state.players.list.any { it.name.isBlank() }
        if (hasEmptyNames) return@also

        val isScoreEndConditionValid = state.endConditions.score.let { score ->
            if (!score.isEnabled) return@let true
            if (score.value.isEmpty()) return@let false

            val value = score.value.toIntOrNull() ?: return@let false
            value > 0
        }
        if (!isScoreEndConditionValid) return@also

        val isTimeEndConditionValid = state.endConditions.time.let { time ->
            if (!time.isEnabled) return@let true
            if (time.hours.isEmpty() || time.minutes.isEmpty()) return@let false

            val hours = time.hours.toIntOrNull() ?: return@let false
            val minutes = time.minutes.toIntOrNull() ?: return@let false
            minutes < 60 && (hours > 0 || minutes > 0)
        }
        if (!isTimeEndConditionValid) return@also

        val config = createGameConfig()

        // TODO loading/disable button?
        // TODO error handling
        startGameJob = viewModelScope.launch {
            startGameUseCase(config)

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

        val value = condition.value.toIntOrNull() ?: return null
        return Score(value)
    }

    private fun resolveEndConditionTime(): Duration? {
        val condition = state.endConditions.time
        if (!condition.isEnabled) return null

        val hours = condition.hours.toIntOrNull() ?: return null
        val minutes = condition.minutes.toIntOrNull() ?: return null
        return hours.hours + minutes.minutes
    }

    private inline fun updatePlayersState(
        build: GameStartState.Players.() -> GameStartState.Players,
    ): GameStartState = updateState {
        copy(players = players.build())
    }

    private inline fun updateEndConditionsState(
        build: GameStartState.EndConditions.() -> GameStartState.EndConditions,
    ): GameStartState = updateState {
        copy(endConditions = endConditions.build())
    }

    private inline fun updateScoreEndConditionState(
        build: ScoreEndConditionState.() -> ScoreEndConditionState,
    ): GameStartState = updateEndConditionsState {
        copy(score = score.build())
    }

    private inline fun updateTimeEndConditionState(
        build: TimeEndConditionState.() -> TimeEndConditionState,
    ): GameStartState = updateEndConditionsState {
        copy(time = time.build())
    }

    private fun String.filterDigits(maxLength: Int): String {
        return take(maxLength).filter { it.isDigit() }
    }
}
