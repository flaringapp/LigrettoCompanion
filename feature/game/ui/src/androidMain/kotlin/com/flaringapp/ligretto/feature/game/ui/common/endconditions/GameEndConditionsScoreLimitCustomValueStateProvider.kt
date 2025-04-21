package com.flaringapp.ligretto.feature.game.ui.common.endconditions

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.core.ui.ext.asDescription
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState.CustomInput

internal class GameEndConditionsScoreLimitCustomValueStateProvider :
    PreviewParameterProvider<CustomInput> {

    companion object {

        fun empty() = CustomInput(
            value = "",
            error = null,
        )

        fun filled() = empty().copy(
            value = "123",
        )

        fun error() = empty().copy(
            error = "Enter a valid score".asDescription(),
        )
    }

    override val values: Sequence<CustomInput> = sequenceOf(
        empty(),
        filled(),
        error(),
    )
}
