package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.bg_winner
import ligretto_companion.feature.game.ui.generated.resources.game_end_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameEndFirstPlaceCard(
    state: GameEndState.PlayerResult?,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    Surface(
        modifier = modifier,
        color = Color.Unspecified,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp),
    ) {
        Box(
            propagateMinConstraints = true,
        ) {
            BackgroundImage(
                modifier = Modifier.matchParentSize(),
            )

            ActualContent(
                modifier = Modifier.padding(contentPadding),
                state = state,
            )
        }
    }
}

@Composable
private fun BackgroundImage(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier,
        painter = painterResource(Res.drawable.bg_winner),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun ActualContent(
    state: GameEndState.PlayerResult?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderText()

        state?.let {
            GameEndFirstPlace(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                state = it,
            )
        }
    }
}

@Composable
private fun HeaderText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.game_end_title),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineMedium,
    )
}
