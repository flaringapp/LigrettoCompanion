package com.flaringapp.ligretto.feature.game.ui.lap.start.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
internal fun GameLapStartBackgroundNumber(
    lapNumber: Int,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val screenHeight = constraints.maxHeight

        if (lapNumber < 10) {
            SingleDigitLapNumber(
                modifier = modifier,
                digit = lapNumber.coerceAtLeast(0).toString(),
                screenHeight = screenHeight,
            )
            return@BoxWithConstraints
        }

        val lapNumberString = lapNumber.toString()
        TwoDigitLapNumber(
            modifier = modifier,
            firstDigit = lapNumberString[lapNumberString.lastIndex - 1].toString(),
            secondDigit = lapNumberString[lapNumberString.lastIndex].toString(),
            screenHeight = screenHeight,
        )
    }
}

@Composable
private fun SingleDigitLapNumber(
    digit: String,
    screenHeight: Int,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val fontSize = with(density) {
        screenHeight.toSp() * 1.525f
    }
    val horizontalOffset = with(density) {
        (screenHeight * 0.055f).toDp()
    }

    DigitText(
        modifier = modifier.offset(x = -horizontalOffset),
        digit = digit,
        fontSize = fontSize,
        alignment = Alignment.CenterStart,
    )
}

@Composable
private fun TwoDigitLapNumber(
    firstDigit: String,
    secondDigit: String,
    screenHeight: Int,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val fontSize = with(density) {
        (screenHeight * 0.75f).toSp()
    }
    val spacing = with(density) {
        (fontSize.toDp() / 2) - 40.dp
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(-spacing),
    ) {
        DigitText(
            modifier = Modifier.weight(1f),
            digit = firstDigit,
            fontSize = fontSize,
            alignment = Alignment.BottomCenter,
        )

        DigitText(
            modifier = Modifier.weight(1f),
            digit = secondDigit,
            fontSize = fontSize,
            alignment = Alignment.TopCenter,
        )
    }
}

@Composable
private fun DigitText(
    modifier: Modifier = Modifier,
    digit: String,
    fontSize: TextUnit,
    alignment: Alignment,
) {
    Text(
        modifier = modifier.wrapContentSize(unbounded = true, align = alignment),
        text = digit,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        lineHeight = 1.em,
        style = MaterialTheme.typography.displayLarge.copy(
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            ),
        ),
    )
}
