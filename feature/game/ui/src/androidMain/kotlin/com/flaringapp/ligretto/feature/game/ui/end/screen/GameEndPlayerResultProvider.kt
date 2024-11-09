package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState.PlayerResult

internal class GameEndPlayerResultProvider : PreviewParameterProvider<PlayerResult> {

    companion object {
        fun default() = PlayerResult(
            name = "Andreo",
            score = 102,
        )

        fun long() = default().copy(
            name = "Andreolabadubidus",
        )
    }

    override val values: Sequence<PlayerResult> = sequenceOf(
        default(),
        long(),
    )
}
