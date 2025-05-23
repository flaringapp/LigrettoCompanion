package com.flaringapp.ligretto.feature.game.ui.score.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.ui.components.FooterButton
import com.flaringapp.ligretto.core.ui.ext.fadingEdges
import com.flaringapp.ligretto.feature.game.ui.common.menu.GameInProgressTopBarOverflowIconWithMenu
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreIntent
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.scores_start_next_lap
import ligretto_companion.feature.game.ui.generated.resources.scores_title
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GameScoreScreenContent(
    state: GameScoreState,
    dispatch: (GameScoreIntent) -> Unit,
    onChangeSettingsClick: () -> Unit,
    onFinishGameClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(Res.string.scores_title))
                },
                actions = {
                    GameInProgressTopBarOverflowIconWithMenu(
                        onChangeSettingsClick = onChangeSettingsClick,
                        onFinishGameClick = onFinishGameClick,
                    )
                },
            )
        },
        bottomBar = {
            NextRoundButton(
                roundNumber = state.nextRoundNumber,
                onClick = { dispatch(GameScoreIntent.StartNextLap) },
            )
        },
        content = { innerPadding ->
            ScoresContentContent(
                modifier = Modifier
                    .consumeWindowInsets(innerPadding)
                    .padding(innerPadding),
                state = state,
            )
        },
    )
}

@Composable
private fun ScoresContentContent(
    state: GameScoreState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .fadingEdges(PaddingValues(bottom = 32.dp))
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, bottom = 32.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        state.playerScores.forEach { playerScore ->
            GameScorePlayer(
                state = playerScore,
            )
        }

        state.endConditions?.let {
            GameScoreEndConditionContent(
                modifier = Modifier.padding(vertical = 24.dp),
                state = it,
            )
        }
    }
}

@Composable
private fun NextRoundButton(
    roundNumber: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FooterButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(
            text = stringResource(Res.string.scores_start_next_lap, roundNumber),
        )

        Icon(
            modifier = Modifier.padding(start = 8.dp),
            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
            contentDescription = null,
        )
    }
}
