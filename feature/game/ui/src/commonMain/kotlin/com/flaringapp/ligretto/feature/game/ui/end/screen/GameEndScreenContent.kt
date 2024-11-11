package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.ui.components.FooterButton
import com.flaringapp.ligretto.core.ui.ext.fadingEdges
import com.flaringapp.ligretto.feature.game.ui.end.GameEndIntent
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.game_end_close_button
import ligretto_companion.feature.game.ui.generated.resources.game_end_title
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
            state.winners?.let {
                ActualContent(
                    modifier = Modifier
                        .consumeWindowInsets(innerPadding)
                        .padding(innerPadding),
                    winners = it,
                )
            }
        },
    )
}

@Composable
private fun ActualContent(
    winners: GameEndState.Winners,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fadingEdges(PaddingValues(bottom = 24.dp))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 32.dp),
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

        if (winners.otherPlaces.isNotEmpty()) {
            Spacer(
                modifier = Modifier.height(24.dp),
            )
        }

        winners.otherPlaces.forEachIndexed { index, player ->
            GameEndOtherPlace(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                place = index + 4,
                state = player,
            )
        }
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
