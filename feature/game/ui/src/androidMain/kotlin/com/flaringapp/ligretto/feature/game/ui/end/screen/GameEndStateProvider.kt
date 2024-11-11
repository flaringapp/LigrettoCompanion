package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.asUiList
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState

internal class GameEndStateProvider : PreviewParameterProvider<GameEndState> {

    companion object {

        fun one() = GameEndState(
            scoreboard = generateWinners(count = 1),
        )

        fun two() = GameEndState(
            scoreboard = generateWinners(count = 2),
        )

        fun three() = GameEndState(
            scoreboard = generateWinners(count = 3),
        )

        fun aLot() = GameEndState(
            scoreboard = generateWinners(count = 15),
        )

        private fun generateWinners(count: Int): UiList<GameEndState.PlayerResult> {
            return List(count) {
                GameEndPlayerResultProvider.default()
            }.asUiList()
        }
    }

    override val values: Sequence<GameEndState> = sequenceOf(
        one(),
        two(),
        three(),
        aLot(),
    )
}
