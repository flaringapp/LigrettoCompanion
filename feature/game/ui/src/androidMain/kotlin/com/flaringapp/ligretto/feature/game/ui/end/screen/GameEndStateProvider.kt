package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState

internal class GameEndStateProvider : PreviewParameterProvider<GameEndState> {

    companion object {
        fun one() = GameEndState(
            winners = GameEndState.Winners(
                firstPlace = GameEndPlayerResultProvider.default(),
                secondPlace = null,
                thirdPlace = null,
            ),
        )

        fun two() = GameEndState(
            winners = GameEndState.Winners(
                firstPlace = GameEndPlayerResultProvider.default(),
                secondPlace = GameEndPlayerResultProvider.default(),
                thirdPlace = null,
            ),
        )

        fun three() = GameEndState(
            winners = GameEndState.Winners(
                firstPlace = GameEndPlayerResultProvider.default(),
                secondPlace = GameEndPlayerResultProvider.default(),
                thirdPlace = GameEndPlayerResultProvider.default(),
            ),
        )
    }

    override val values: Sequence<GameEndState> = sequenceOf(
        one(),
        two(),
        three(),
    )
}
