package com.flaringapp.ligretto.feature.game.ui.common.endconditions

import com.flaringapp.ligretto.core.arch.Reducer
import com.flaringapp.ligretto.core.ui.ext.StringDescription
import com.flaringapp.ligretto.core.ui.ext.StringDescriptionResWithArgs
import com.flaringapp.ligretto.core.ui.ext.asDescription
import com.flaringapp.ligretto.core.util.common.ValueOrError
import com.flaringapp.ligretto.core.util.common.valueOrElse
import com.flaringapp.ligretto.feature.game.domain.model.GameEndConditionCustomScoreValidationError
import com.flaringapp.ligretto.feature.game.domain.model.GameEndConditionCustomTimeValidationError
import com.flaringapp.ligretto.feature.game.domain.usecase.ValidateGameEndConditionCustomScoreUseCase
import com.flaringapp.ligretto.feature.game.domain.usecase.ValidateGameEndConditionCustomTimeUseCase
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.game_settings_custom_score_invalid_error
import ligretto_companion.feature.game.ui.generated.resources.game_settings_custom_score_too_long_error
import ligretto_companion.feature.game.ui.generated.resources.game_settings_custom_time_invalid_error
import ligretto_companion.feature.game.ui.generated.resources.game_settings_custom_time_too_long_error
import org.koin.core.annotation.Factory

@Factory
internal class GameEndConditionsScoreReducer(
    private val validateCustomScoreUseCase: ValidateGameEndConditionCustomScoreUseCase,
) : Reducer<GameEndConditionScoreLimitState, GameEndConditionScoreIntent> {

    override fun reduce(
        state: GameEndConditionScoreLimitState,
        intent: GameEndConditionScoreIntent,
    ): GameEndConditionScoreLimitState = when (intent) {
        is GameEndConditionScoreIntent.SetEnabled -> {
            state.copy(
                isEnabled = intent.isEnabled,
            )
        }

        is GameEndConditionScoreIntent.ValueChange -> {
            state.copy(
                selectedScore = intent.score,
            )
        }

        is GameEndConditionScoreIntent.SelectCustomValue -> {
            if (state.lastCustomScore == null ||
                state.lastCustomScore == state.selectedScore
            ) {
                state.copy(
                    customScoreInput = GameEndConditionScoreLimitState.CustomInput(),
                )
            } else {
                state.copy(
                    selectedScore = state.lastCustomScore,
                )
            }
        }

        is GameEndConditionScoreIntent.NewCustomValueChange -> {
            state.copy(
                customScoreInput = state.customScoreInput?.copy(
                    value = intent.score,
                    error = null,
                ),
            )
        }

        is GameEndConditionScoreIntent.SubmitNewCustomValue -> {
            submitNewCustomValue(state)
        }

        is GameEndConditionScoreIntent.DismissNewCustomValue -> {
            state.copy(
                customScoreInput = null,
            )
        }
    }

    private fun submitNewCustomValue(
        state: GameEndConditionScoreLimitState,
    ): GameEndConditionScoreLimitState {
        val inputState = state.customScoreInput ?: return state
        val score = resolveValidatedScore(inputState).valueOrElse { error ->
            return state.copy(
                customScoreInput = inputState.copy(
                    error = mapValidationError(error),
                ),
            )
        }

        val presentInStaticOptions = state.staticOptions.contains(score)

        return state.copy(
            selectedScore = score,
            lastCustomScore = score.takeIf { !presentInStaticOptions },
            customScoreInput = null,
        )
    }

    private fun resolveValidatedScore(
        state: GameEndConditionScoreLimitState.CustomInput,
    ): ValueOrError<Int, GameEndConditionCustomScoreValidationError> {
        val score = state.value.toIntOrNull() ?: run {
            return ValueOrError.Error(error = GameEndConditionCustomScoreValidationError.Empty)
        }

        validateCustomScoreUseCase(score)?.let {
            return ValueOrError.Error(error = it)
        }

        return ValueOrError.Value(score)
    }

    private fun mapValidationError(
        error: GameEndConditionCustomScoreValidationError,
    ): StringDescription = when (error) {
        GameEndConditionCustomScoreValidationError.Empty -> {
            Res.string.game_settings_custom_score_invalid_error.asDescription()
        }

        is GameEndConditionCustomScoreValidationError.TooLarge -> {
            StringDescriptionResWithArgs(
                resource = Res.string.game_settings_custom_score_too_long_error,
                args = listOf(error.maxScore),
            )
        }
    }
}

@Factory
internal class GameEndConditionsTimeReducer(
    private val validateCustomTimeUseCase: ValidateGameEndConditionCustomTimeUseCase,
) : Reducer<GameEndConditionTimeLimitState, GameEndConditionTimeIntent> {

    override fun reduce(
        state: GameEndConditionTimeLimitState,
        intent: GameEndConditionTimeIntent,
    ): GameEndConditionTimeLimitState = when (intent) {
        is GameEndConditionTimeIntent.SetEnabled -> {
            state.copy(
                isEnabled = intent.isEnabled,
            )
        }

        is GameEndConditionTimeIntent.ValueChange -> {
            state.copy(
                selectedMinutes = intent.minutes,
            )
        }

        is GameEndConditionTimeIntent.SelectCustomValue -> {
            if (state.lastCustomMinutes == null ||
                state.lastCustomMinutes == state.selectedMinutes
            ) {
                state.copy(
                    customMinutesInput = GameEndConditionTimeLimitState.CustomInput(),
                )
            } else {
                state.copy(
                    selectedMinutes = state.lastCustomMinutes,
                )
            }
        }

        is GameEndConditionTimeIntent.NewCustomValueChange -> {
            state.copy(
                customMinutesInput = state.customMinutesInput?.copy(
                    value = intent.minutes,
                    error = null,
                ),
            )
        }

        is GameEndConditionTimeIntent.SubmitNewCustomValue -> {
            submitNewCustomValue(state)
        }

        is GameEndConditionTimeIntent.DismissNewCustomValue -> {
            state.copy(
                customMinutesInput = null,
            )
        }
    }

    private fun submitNewCustomValue(
        state: GameEndConditionTimeLimitState,
    ): GameEndConditionTimeLimitState {
        val inputState = state.customMinutesInput ?: return state
        val minutes = resolveValidatedMinutes(inputState).valueOrElse { error ->
            return state.copy(
                customMinutesInput = inputState.copy(
                    error = mapValidationError(error),
                ),
            )
        }

        val presentInStaticOptions = state.staticOptions.contains(minutes)

        return state.copy(
            selectedMinutes = minutes,
            lastCustomMinutes = minutes.takeIf { !presentInStaticOptions },
            customMinutesInput = null,
        )
    }

    private fun resolveValidatedMinutes(
        state: GameEndConditionTimeLimitState.CustomInput,
    ): ValueOrError<Int, GameEndConditionCustomTimeValidationError> {
        val minutes = state.value.toIntOrNull() ?: run {
            return ValueOrError.Error(error = GameEndConditionCustomTimeValidationError.Empty)
        }

        validateCustomTimeUseCase(minutes)?.let {
            return ValueOrError.Error(error = it)
        }

        return ValueOrError.Value(minutes)
    }

    private fun mapValidationError(
        error: GameEndConditionCustomTimeValidationError,
    ): StringDescription = when (error) {
        GameEndConditionCustomTimeValidationError.Empty -> {
            Res.string.game_settings_custom_time_invalid_error.asDescription()
        }

        is GameEndConditionCustomTimeValidationError.TooLarge -> {
            StringDescriptionResWithArgs(
                resource = Res.string.game_settings_custom_time_too_long_error,
                args = listOf(error.maxMinutes),
            )
        }
    }
}
