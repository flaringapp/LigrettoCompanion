package com.flaringapp.ligretto.feature.game.ui.common.endconditions

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.core.ui.ext.asDescription
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState.CustomInput

internal class GameEndConditionsTimeLimitCustomValueStateProvider :
    PreviewParameterProvider<CustomInput> {

    companion object {

        fun empty() = CustomInput(
            value = "",
            error = null,
        )

        fun filled() = empty().copy(
            value = "65",
        )

        fun error() = empty().copy(
            error = "Enter a valid time".asDescription(),
        )
    }

    override val values: Sequence<CustomInput> = sequenceOf(
        empty(),
        filled(),
        error(),
    )
}
