package com.flaringapp.ligretto.core.designsystem

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import ligretto_companion.core.designsystem.generated.resources.Res
import ligretto_companion.core.designsystem.generated.resources.outfit_bold
import ligretto_companion.core.designsystem.generated.resources.outfit_light
import ligretto_companion.core.designsystem.generated.resources.outfit_medium
import ligretto_companion.core.designsystem.generated.resources.outfit_regular
import ligretto_companion.core.designsystem.generated.resources.roboto_serif_bold
import ligretto_companion.core.designsystem.generated.resources.roboto_serif_regular
import org.jetbrains.compose.resources.Font

@Composable
fun createAppTypography(): Typography {
    return createAppTypography(
        robotoSerif = AppFontFamilies.robotoSerif(),
        outfit = AppFontFamilies.outfit(),
    )
}

private fun createAppTypography(
    robotoSerif: FontFamily,
    outfit: FontFamily,
): Typography {
    val basicTextStyle = TextStyle(
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None,
        ),
    )
    val headerTextStyle = basicTextStyle.copy(
        fontFamily = robotoSerif,
    )
    val contentTextStyle = basicTextStyle.copy(
        fontFamily = outfit,
    )

    return Typography(
        displayLarge = headerTextStyle.copy(
            fontSize = 56.sp,
            lineHeight = 64.sp,
        ),
        displayMedium = headerTextStyle.copy(
            fontSize = 44.sp,
            lineHeight = 52.sp,
        ),
        displaySmall = headerTextStyle.copy(
            fontSize = 36.sp,
            lineHeight = 44.sp,
        ),
        headlineLarge = headerTextStyle.copy(
            fontSize = 36.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight.Bold,
        ),
        headlineMedium = headerTextStyle.copy(
            fontSize = 30.sp,
            lineHeight = 36.sp,
            fontWeight = FontWeight.Bold,
        ),
        headlineSmall = contentTextStyle.copy(
            fontSize = 24.sp,
            lineHeight = 28.sp,
        ),
        titleLarge = contentTextStyle.copy(
            fontSize = 22.sp,
            lineHeight = 28.sp,
        ),
        titleMedium = contentTextStyle.copy(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
        ),
        titleSmall = contentTextStyle.copy(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
        ),
        bodyLarge = contentTextStyle.copy(
            fontSize = 16.sp,
            lineHeight = 24.sp,
        ),
        bodyMedium = contentTextStyle.copy(
            fontSize = 14.sp,
            lineHeight = 20.sp,
        ),
        bodySmall = contentTextStyle.copy(
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
        labelLarge = contentTextStyle.copy(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
        ),
        labelMedium = contentTextStyle.copy(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Medium,
        ),
        labelSmall = contentTextStyle.copy(
            fontSize = 11.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Medium,
        ),
    )
}

object AppFontFamilies {

    @Composable
    fun robotoSerif() = FontFamily(
        Font(Res.font.roboto_serif_regular, FontWeight.Normal),
        Font(Res.font.roboto_serif_bold, FontWeight.Bold),
    )

    @Composable
    fun outfit() = FontFamily(
        Font(Res.font.outfit_light, FontWeight.Light),
        Font(Res.font.outfit_regular, FontWeight.Normal),
        Font(Res.font.outfit_medium, FontWeight.Medium),
        Font(Res.font.outfit_bold, FontWeight.Bold),
    )
}
