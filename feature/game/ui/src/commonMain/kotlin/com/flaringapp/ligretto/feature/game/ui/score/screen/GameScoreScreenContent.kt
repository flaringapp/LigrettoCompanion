package com.flaringapp.ligretto.feature.game.ui.score.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.components.HeaderText
import com.flaringapp.ligretto.core.ui.ext.screenWindowInsetsPadding
import com.flaringapp.ligretto.core.ui.misc.SnapLastItemToBottomArrangement
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreIntent
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState
import com.flaringapp.ligretto.feature.game.ui.score.screen.preview.GameScoreStateProvider
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.scores_start_next_lap
import ligretto_companion.feature.game.ui.generated.resources.scores_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

private const val CONTENT_TYPE_HEADER = "header"
private const val CONTENT_TYPE_END_CONDITIONS = "end_conditions"
private const val CONTENT_TYPE_SCORE = "score"
private const val CONTENT_TYPE_BUTTONS = "buttons"

@Composable
internal fun GameScoreScreenContent(
    state: GameScoreState,
    dispatch: (GameScoreIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lastScoreIndex = remember(state.scores.size) {
        state.scores.lastIndex
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .screenWindowInsetsPadding(),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = remember { SnapLastItemToBottomArrangement() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item(contentType = CONTENT_TYPE_HEADER) {
            Header(modifier = Modifier.padding(bottom = 16.dp))
        }
        state.endConditions?.let {
            item(contentType = CONTENT_TYPE_END_CONDITIONS) {
                GameScoreEndConditions(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    state = it,
                )
            }
        }
        itemsIndexed(
            items = state.scores,
            key = { _, score -> "$CONTENT_TYPE_SCORE${score.id}" },
            contentType = { _, _ -> CONTENT_TYPE_SCORE },
        ) { index, score ->
            GameScorePlayer(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                name = score.playerName,
                score = score.score,
            )

            if (index != lastScoreIndex) {
                HorizontalDivider(Modifier.padding(horizontal = 16.dp))
            }
        }
        item(contentType = CONTENT_TYPE_BUTTONS) {
            Buttons(
                modifier = Modifier.padding(top = 16.dp),
                onStartNextLap = { dispatch(GameScoreIntent.StartNextLap) },
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
        text = stringResource(Res.string.scores_title),
    )
}

@Composable
private fun Buttons(
    onStartNextLap: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        NextLapButton(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp),
            onClick = onStartNextLap,
        )
    }
}

@Composable
private fun NextLapButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilledIconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            painter = rememberVectorPainter(Icons.AutoMirrored.Rounded.KeyboardArrowRight),
            contentDescription = stringResource(Res.string.scores_start_next_lap),
        )
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(GameScoreStateProvider::class) state: GameScoreState
) {
    AppTheme {
        GameScoreScreenContent(
            state = state,
            dispatch = {},
        )
    }
}
