package com.flaringapp.ligretto.feature.game.ui.score.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState.PlayerScore
import com.flaringapp.ligretto.feature.game.ui.score.screen.preview.GameScorePlayerScoreProvider
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.ic_first_place
import ligretto_companion.feature.game.ui.generated.resources.ic_second_place
import ligretto_companion.feature.game.ui.generated.resources.ic_third_place
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

private val PlaceTextStyle: TextStyle
    @Composable
    get() = MaterialTheme.typography.titleMedium

@Composable
internal fun GameScorePlayer(
    state: PlayerScore,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = RoundedCornerShape(16.dp),
    ) {
        ActualContent(
            state = state,
        )
    }
}

@Composable
private fun ActualContent(
    state: PlayerScore,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(top = 16.dp, bottom = 16.dp, start = 12.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Place(
            modifier = Modifier.width(40.dp),
            place = state.place,
        )

        PlayerNameText(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 16.dp),
            name = state.playerName,
        )

        ScoreText(
            score = state.score,
        )
    }
}

@Composable
private fun Place(
    place: Int,
    modifier: Modifier = Modifier,
) {
    val placeImageRes = when (place) {
        1 -> Res.drawable.ic_first_place
        2 -> Res.drawable.ic_second_place
        3 -> Res.drawable.ic_third_place
        else -> null
    }
    val textStyle = PlaceTextStyle

    Box(
        modifier = modifier,
        propagateMinConstraints = true,
    ) {
        placeImageRes?.let {
            PlaceImage(
                imageRes = placeImageRes,
                textStyle = textStyle,
            )
            return@Box
        }

        PlaceText(
            place = place,
            textStyle = textStyle,
        )
    }
}

@Composable
private fun PlaceImage(
    imageRes: DrawableResource,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier
            .height(
                with(LocalDensity.current) { textStyle.lineHeight.toDp() }
            ),
        painter = painterResource(imageRes),
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
    )
}

@Composable
private fun PlaceText(
    place: Int,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = place.toString(),
        textAlign = TextAlign.Center,
        style = textStyle,
    )
}

@Composable
private fun PlayerNameText(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = name,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
private fun ScoreText(
    score: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = score.toString(),
        style = MaterialTheme.typography.headlineMedium,
    )
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(GameScorePlayerScoreProvider::class)
    state: PlayerScore
) {
    AppTheme {
        GameScorePlayer(
            state = state,
        )
    }
}
