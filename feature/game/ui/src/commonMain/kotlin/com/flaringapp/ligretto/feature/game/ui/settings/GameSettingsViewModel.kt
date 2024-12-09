package com.flaringapp.ligretto.feature.game.ui.settings

import com.flaringapp.ligretto.core.arch.MviViewModel
import com.flaringapp.ligretto.feature.game.domain.usecase.ChangeGameSettingsUseCase
import com.flaringapp.ligretto.feature.game.model.Score
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
    private val changeGameSettingsUseCase: ChangeGameSettingsUseCase,
) : MviViewModel<GameSettingsState, GameSettingsIntent, GameSettingsEffect>(
    GameSettingsState(),
) {

    private var saveJob: Job? = null

    override fun reduce(
        state: GameSettingsState,
        intent: GameSettingsIntent,
    ): GameSettingsState = when (intent) {
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

    private fun save() = state.also {
        if (saveJob?.isCompleted == false) {
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
