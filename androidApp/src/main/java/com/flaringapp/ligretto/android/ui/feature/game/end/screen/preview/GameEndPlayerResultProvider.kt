package com.flaringapp.ligretto.android.ui.feature.game.end.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.android.ui.feature.game.end.GameEndState.PlayerResult

class GameEndPlayerResultProvider : PreviewParameterProvider<PlayerResult> {

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
