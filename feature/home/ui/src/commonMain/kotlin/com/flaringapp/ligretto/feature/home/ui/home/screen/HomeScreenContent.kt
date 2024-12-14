package com.flaringapp.ligretto.feature.home.ui.home.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.ui.ext.AnimateInitialEnter
import com.flaringapp.ligretto.core.ui.ext.animateInitialEnter
import com.flaringapp.ligretto.core.ui.ext.screen
import com.flaringapp.ligretto.core.ui.ext.screenStatusBarScrim
import com.flaringapp.ligretto.core.ui.ext.screenWindowInsetsPadding
import com.flaringapp.ligretto.feature.home.ui.home.HomeIntent
import com.flaringapp.ligretto.feature.home.ui.home.HomeState
import ligretto_companion.feature.home.ui.generated.resources.Res
import ligretto_companion.feature.home.ui.generated.resources.home_continue_game_button
import ligretto_companion.feature.home.ui.generated.resources.home_message
import ligretto_companion.feature.home.ui.generated.resources.home_start_first_game_button
import ligretto_companion.feature.home.ui.generated.resources.home_start_next_game_button
import ligretto_companion.feature.home.ui.generated.resources.home_title
import ligretto_companion.feature.home.ui.generated.resources.img_home_header
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

private val contentCornerSize: Dp
    get() = 32.dp

@Composable
internal fun HomeScreenContent(
    state: HomeState,
    dispatch: (HomeIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .screenStatusBarScrim(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
            )
            .consumeWindowInsets(
                WindowInsets.screen.only(WindowInsetsSides.Top),
            ),
        verticalArrangement = Arrangement.spacedBy(-contentCornerSize),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimateInitialEnter(
            label = "HeaderImageInitialAnimation",
            transition = fadeIn() + scaleIn(
                initialScale = 1.4f,
                animationSpec = spring(stiffness = Spring.StiffnessLow),
            ),
        ) {
            HeaderImage()
        }

        AnimateInitialEnter(
            label = "ContentUnderHeaderInitialAnimation",
            transition = slideInVertically(
                initialOffsetY = { it / 4 },
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                ),
            ),
        ) {
            ContentUnderHeader(
                modifier = Modifier.fillMaxSize(),
                state = state,
                dispatch = dispatch,
            )
        }
    }
}

@Composable
private fun HeaderImage(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier.fillMaxWidth(),
        painter = painterResource(Res.drawable.img_home_header),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
    )
}

@Composable
private fun AnimatedVisibilityScope.ContentUnderHeader(
    state: HomeState,
    dispatch: (HomeIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(topStart = contentCornerSize, topEnd = contentCornerSize),
    ) {
        Column(
            modifier = Modifier
                .screenWindowInsetsPadding()
                .padding(top = 48.dp, bottom = 80.dp, start = 24.dp, end = 24.dp)
                .animateInitialEnter(
                    scope = this,
                    label = "ContentUnderHeaderContentInitialAnimation",
                    transition = fadeIn(),
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TitleText()

            MessageText(
                modifier = Modifier.padding(top = 32.dp),
            )

            Spacer(
                modifier = Modifier.weight(1f),
            )

            StartGameButton(
                modifier = Modifier.fillMaxWidth(),
                hasActiveGame = state.hasActiveGame,
                onClick = { dispatch(HomeIntent.StartGame) },
            )

            if (state.hasActiveGame) {
                ContinueGameButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    onClick = { dispatch(HomeIntent.ContinueActiveGame) },
                )
            }
        }
    }
}

@Composable
private fun TitleText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.home_title),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineMedium,
    )
}

@Composable
private fun MessageText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.home_message),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
private fun StartGameButton(
    hasActiveGame: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val textRes = if (hasActiveGame) {
        Res.string.home_start_next_game_button
    } else {
        Res.string.home_start_first_game_button
    }

    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(vertical = 14.dp, horizontal = 24.dp),
    ) {
        Text(
            text = stringResource(textRes),
        )
    }
}

@Composable
private fun ContinueGameButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilledTonalButton(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(vertical = 14.dp, horizontal = 24.dp),
    ) {
        Text(
            text = stringResource(Res.string.home_continue_game_button),
        )
    }
}
