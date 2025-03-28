package com.flaringapp.ligretto.feature.game.ui.settings

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.core.arch.dispatch
import com.flaringapp.ligretto.core.util.common.isRunning
import com.flaringapp.ligretto.feature.game.domain.usecase.ChangeGameSettingsUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.GetCurrentGameUseCase
import com.flaringapp.ligretto.feature.game.model.Score
import com.flaringapp.ligretto.feature.game.model.end.GameEndConditions
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsScoreReducer
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsTimeReducer
import org.koin.android.annotation.KoinViewModel
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@KoinViewModel
internal class GameSettingsViewModel(
    private val getCurrentGameUseCase: GetCurrentGameUseCase,
    private val changeGameSettingsUseCase: ChangeGameSettingsUseCase,
) : MviViewModel<GameSettingsState, GameSettingsIntent, GameSettingsEffect>(
    GameSettingsState(),
) {

    private var saveJob: Job? = null

    init {
        dispatch { GameSettingsIntent.LoadData }
    }

    override fun reduce(
        state: GameSettingsState,
        intent: GameSettingsIntent,
    ): GameSettingsState = when (intent) {
        is GameSettingsIntent.LoadData -> {
            loadData()
        }

        is GameSettingsIntent.InitData -> intent.state

        is GameSettingsIntent.EndConditions.Score -> {
            state.copy(
                score = GameEndConditionsScoreReducer.reduce(
                    state = state.score,
                    intent = intent.intent,
                ),
            )
        }

        is GameSettingsIntent.EndConditions.Time -> {
            state.copy(
                time = GameEndConditionsTimeReducer.reduce(
                    state = state.time,
                    intent = intent.intent,
                ),
            )
        }

        GameSettingsIntent.Save -> save()
    }

    private fun loadData() = state.also {
        val game = getCurrentGameUseCase().value ?: return@also
        val endConditions = game.endConditions

        val state = mapInitialState(endConditions)

        dispatch { GameSettingsIntent.InitData(state) }
    }

    private fun mapInitialState(
        endConditions: GameEndConditions,
    ): GameSettingsState {
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

        return GameSettingsState(
            score = score,
            time = time,
        )
    }

    private fun save() = state.also {
        if (saveJob.isRunning) {
            return@also
        }

        val score = state.score.run {
            selectedScore.takeIf { isEnabled }?.let(::Score)
        }
        val time = state.time.run {
            selectedMinutes.takeIf { isEnabled }?.minutes
        }

        saveJob = viewModelScope.launch(Dispatchers.IO) {
            changeGameSettingsUseCase.invoke(
                targetScore = score,
                timeLimit = time,
            )

            setEffect { GameSettingsEffect.Close }
        }
    }
}
