package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.ui.components.HeaderText
import com.flaringapp.ligretto.core.ui.ext.screenWindowInsetsPadding
import com.flaringapp.ligretto.feature.game.ui.end.GameEndIntent
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.end_close
import ligretto_companion.feature.game.ui.generated.resources.end_second_place_one
import ligretto_companion.feature.game.ui.generated.resources.end_second_place_two
import ligretto_companion.feature.game.ui.generated.resources.end_third_place_one
import ligretto_companion.feature.game.ui.generated.resources.end_third_place_two
import ligretto_companion.feature.game.ui.generated.resources.end_title
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameEndScreenContent(
    state: GameEndState,
    dispatch: (GameEndIntent) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .screenWindowInsetsPadding()
                .padding(24.dp),
        ) {
            state.winners?.let {
                ActualContent(
                    modifier = Modifier.align(Alignment.Center),
                    winners = it,
                )
            }

            CloseButton(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = { dispatch(GameEndIntent.Finish) },
            )
        }
    }
}

@Composable
private fun ActualContent(
    winners: GameEndState.Winners,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header()

        GameEndFirstPlace(
            modifier = Modifier.padding(top = 32.dp),
            state = winners.firstPlace,
        )
        winners.secondPlace?.let {
            SecondPlace(
                modifier = Modifier.padding(top = 16.dp),
                state = it,
            )
        }
        winners.thirdPlace?.let {
            ThirdPlace(
                modifier = Modifier.padding(top = 8.dp),
                state = it,
            )
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
) {
    HeaderText(
        modifier = modifier,
        text = stringResource(Res.string.end_title),
    )
}

@Composable
private fun SecondPlace(
    state: GameEndState.PlayerResult,
    modifier: Modifier = Modifier,
) {
    GameEndOtherPlace(
        modifier = modifier,
        state = state,
        placeNumber = stringResource(Res.string.end_second_place_one),
        placePrefix = stringResource(Res.string.end_second_place_two),
    )
}

@Composable
private fun ThirdPlace(
    state: GameEndState.PlayerResult,
    modifier: Modifier = Modifier,
) {
    GameEndOtherPlace(
        modifier = modifier,
        state = state,
        placeNumber = stringResource(Res.string.end_third_place_one),
        placePrefix = stringResource(Res.string.end_third_place_two),
    )
}

@Composable
private fun CloseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(
            text = stringResource(Res.string.end_close),
        )
    }
}
