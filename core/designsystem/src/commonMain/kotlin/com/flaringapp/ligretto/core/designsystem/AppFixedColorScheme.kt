package com.flaringapp.ligretto.core.designsystem

import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse

@Immutable
object AppFixedColorScheme {

    val PrimaryFixed = AppColorPalette.primary90
    val OnPrimaryFixed = AppColorPalette.primary10
    val PrimaryFixedDim = AppColorPalette.primary80
    val OnPrimaryFixedVariant = AppColorPalette.primary30

    val SecondaryFixed = AppColorPalette.secondary90
    val OnSecondaryFixed = AppColorPalette.secondary10
    val SecondaryFixedDim = AppColorPalette.secondary80
    val OnSecondaryFixedVariant = AppColorPalette.secondary30

    val TertiaryFixed = AppColorPalette.tertiary90
    val OnTertiaryFixed = AppColorPalette.tertiary10
    val TertiaryFixedDim = AppColorPalette.tertiary80
    val OnTertiaryFixedVariant = AppColorPalette.tertiary30
}

fun AppFixedColorScheme.contentColorFor(
    fixedBackgroundColor: Color,
): Color = when (fixedBackgroundColor) {
    PrimaryFixed -> OnPrimaryFixed
    PrimaryFixedDim -> OnPrimaryFixedVariant
    SecondaryFixed -> OnSecondaryFixed
    SecondaryFixedDim -> OnSecondaryFixedVariant
    TertiaryFixed -> OnTertiaryFixed
    TertiaryFixedDim -> OnTertiaryFixedVariant
    else -> Color.Unspecified
}

@Composable
fun fixedOrDynamicContentColorFor(backgroundColor: Color): Color =
    AppFixedColorScheme.contentColorFor(backgroundColor)
        .takeOrElse { contentColorFor(backgroundColor) }
