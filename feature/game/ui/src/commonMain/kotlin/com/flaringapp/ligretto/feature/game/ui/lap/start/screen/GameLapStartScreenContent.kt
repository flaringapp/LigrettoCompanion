package com.flaringapp.ligretto.feature.game.ui.lap.start.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flaringapp.ligretto.feature.game.ui.lap.start.GameLapStartIntent
import com.flaringapp.ligretto.feature.game.ui.lap.start.GameLapStartState
import ligretto_companion.core.ui.generated.resources.img_card_blue
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.lap_start_go_text
import ligretto_companion.feature.game.ui.generated.resources.lap_start_round_label
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ligretto_companion.core.ui.generated.resources.Res as CoreRes

@Composable
internal fun GameLapStartScreenContent(
    state: GameLapStartState,
    dispatch: (GameLapStartIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        val lapNumber = state.lapNumber ?: return@Surface

        GameLapStartBackgroundNumber(
            modifier = Modifier.fillMaxSize(),
            lapNumber = lapNumber,
        )

        ActualContent(
            lapNumber = lapNumber,
        )
    }
}

@Composable
private fun ActualContent(
    lapNumber: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(56.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RoundNumberContent(
            lapNumber = lapNumber,
        )

        CardImage()

        GoText()
    }
}

@Composable
private fun RoundNumberContent(
    lapNumber: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RoundLabelText()

        LapNumberText(
            lapNumber = lapNumber,
        )
    }
}

@Composable
private fun RoundLabelText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.lap_start_round_label),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
private fun LapNumberText(
    lapNumber: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = lapNumber.toString(),
        fontSize = 100.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.displayLarge,
    )
}

@Composable
private fun CardImage(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier.height(200.dp),
        painter = painterResource(CoreRes.drawable.img_card_blue),
        contentDescription = null,
    )
}

@Composable
private fun GoText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.lap_start_go_text),
        fontSize = 80.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.displayLarge,
    )
}
