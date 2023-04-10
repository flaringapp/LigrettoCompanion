package com.flaringapp.ligretto.android.ui.feature.game.start

import com.flaringapp.ligretto.android.ui.mvi.MviViewModel
import com.flaringapp.ligretto.core.domain.model.GameConfig
import com.flaringapp.ligretto.core.domain.usecase.StartGameUseCase
import com.flaringapp.ligretto.core.model.Player
import com.flaringapp.ligretto.core.model.Score
import org.koin.android.annotation.KoinViewModel
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

private typealias ScoreEndConditionState = GameStartState.EndConditions.ScoreLimit
private typealias TimeEndConditionState = GameStartState.EndConditions.TimeLimit

@KoinViewModel
class GameStartViewModel(
    private val startGameUseCase: StartGameUseCase,
) : MviViewModel<GameStartState, GameStartIntent, GameStartEffect>(GameStartState()) {

    override fun reduce(
        state: GameStartState,
        intent: GameStartIntent,
    ): GameStartState = when (intent) {
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

    private fun addNewPlayer() = updatePlayersState {
        val id = playersIdCounter + 1
        val newList = list + GameStartState.Player(id, "")
        copy(
            list = newList,
            playersIdCounter = id,
            focusedPlayerId = id,
        )
    }

    private fun changePlayerName(id: Int, name: String) = updatePlayersState {
        val newList = list.map { player ->
            if (player.id != id) return@map player
            player.copy(name = name)
        }
        copy(list = newList)
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
        copy(list = newList)
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
        startGameUseCase(config)

        setEffect { GameStartEffect.StartGame }
        return@also
    }

    private fun createGameConfig(): GameConfig {
        val players = state.players.list.map { player ->
            Player(
                id = player.id,
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
