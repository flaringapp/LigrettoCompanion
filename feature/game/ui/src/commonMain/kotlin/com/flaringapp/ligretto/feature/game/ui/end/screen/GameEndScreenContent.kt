package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.ui.ext.screenWindowInsetsPadding
import com.flaringapp.ligretto.feature.game.ui.end.GameEndIntent
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.end_close
import ligretto_companion.feature.game.ui.generated.resources.game_end_title
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            state = winners.firstPlace,
        )

        SecondAndThirdPlaces(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            secondPlace = winners.secondPlace,
            thirdPlace = winners.thirdPlace,
        )
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.game_end_title),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineMedium,
    )
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
