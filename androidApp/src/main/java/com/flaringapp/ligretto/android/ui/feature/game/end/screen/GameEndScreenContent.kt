package com.flaringapp.ligretto.android.ui.feature.game.end.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.android.R
import com.flaringapp.ligretto.android.ui.AppTheme
import com.flaringapp.ligretto.android.ui.common.HeaderText
import com.flaringapp.ligretto.android.ui.feature.game.end.GameEndIntent
import com.flaringapp.ligretto.android.ui.feature.game.end.GameEndState
import com.flaringapp.ligretto.android.ui.feature.game.end.screen.preview.GameEndStateProvider

@Composable
fun GameEndScreenContent(
    state: GameEndState,
    dispatch: (GameEndIntent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
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
        text = stringResource(R.string.game_end_title),
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
        placeNumber = stringResource(R.string.game_end_second_place_one),
        placePrefix = stringResource(R.string.game_end_second_place_two),
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
        placeNumber = stringResource(R.string.game_end_third_place_one),
        placePrefix = stringResource(R.string.game_end_third_place_two),
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
            text = stringResource(R.string.game_end_close)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(
    @PreviewParameter(GameEndStateProvider::class) state: GameEndState
) {
    AppTheme {
        GameEndScreenContent(
            state = state,
            dispatch = {},
        )
    }
}
