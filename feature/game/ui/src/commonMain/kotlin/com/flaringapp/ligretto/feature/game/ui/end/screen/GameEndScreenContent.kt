package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.ui.components.FooterButton
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.fadingEdges
import com.flaringapp.ligretto.feature.game.ui.end.GameEndIntent
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.game_end_close_button
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameEndScreenContent(
    state: GameEndState,
    dispatch: (GameEndIntent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            FooterButton(
                onClick = { dispatch(GameEndIntent.Finish) },
            ) {
                Text(
                    text = stringResource(Res.string.game_end_close_button),
                )
            }
        },
        content = { innerPadding ->
            val layoutDirection = LocalLayoutDirection.current

            state.scoreboard?.let {
                ActualContent(
                    modifier = Modifier
                        .consumeWindowInsets(innerPadding)
                        .padding(
                            start = innerPadding.calculateStartPadding(layoutDirection),
                            end = innerPadding.calculateEndPadding(layoutDirection),
                            bottom = innerPadding.calculateBottomPadding(),
                        ),
                    scoreboard = it,
                    topInsetPadding = innerPadding.calculateTopPadding(),
                )
            }
        },
    )
}

@Composable
private fun ActualContent(
    scoreboard: UiList<GameEndState.PlayerResult>,
    topInsetPadding: Dp,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GameEndFirstPlaceCard(
            modifier = Modifier.fillMaxWidth(),
            state = scoreboard.firstOrNull(),
            contentPadding = PaddingValues(top = topInsetPadding),
        )

        ScrollableContent(
            scoreboard = scoreboard,
        )
    }
}

@Composable
private fun ScrollableContent(
    scoreboard: UiList<GameEndState.PlayerResult>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fadingEdges(PaddingValues(bottom = 24.dp))
            .verticalScroll(rememberScrollState())
            .padding(vertical = 32.dp, horizontal = 16.dp),
    ) {
        SecondAndThirdPlaces(
            modifier = Modifier.fillMaxWidth(),
            secondPlace = scoreboard.getOrNull(1),
            thirdPlace = scoreboard.getOrNull(2),
        )

        if (scoreboard.size > 3) {
            OtherPlacesContent(
                modifier = Modifier.padding(top = 32.dp),
                scoreboard = scoreboard,
            )
        }
    }
}

@Composable
private fun SecondAndThirdPlaces(
    secondPlace: GameEndState.PlayerResult?,
    thirdPlace: GameEndState.PlayerResult?,
    modifier: Modifier = Modifier,
) {
    if (secondPlace == null && thirdPlace == null) {
        return
    }

    Row(
        modifier = modifier,
    ) {
        secondPlace?.let {
            GameEndSecondaryPlace(
                modifier = Modifier.weight(1f),
                place = 2,
                state = it,
            )
        }

        thirdPlace?.let {
            GameEndSecondaryPlace(
                modifier = Modifier.weight(1f),
                place = 3,
                state = it,
            )
        }
    }
}

@Composable
private fun OtherPlacesContent(
    scoreboard: UiList<GameEndState.PlayerResult>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        scoreboard.asSequence().drop(3).forEachIndexed { index, player ->
            GameEndOtherPlace(
                modifier = Modifier.fillMaxWidth(),
                place = index + 4,
                state = player,
            )
        }
    }
}
