package com.flaringapp.ligretto.common.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.home.ui.HomeDestination

@Composable
fun AppContent() {
    AppTheme {
        RootScreen()
    }
}

@Composable
private fun RootScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = HomeDestination.screenId,
            builder = { appNavGraph(navController) },
        )
    }
}
