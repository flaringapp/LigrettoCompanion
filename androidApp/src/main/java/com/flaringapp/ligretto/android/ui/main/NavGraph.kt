package com.flaringapp.ligretto.android.ui.main

import androidx.navigation.NavGraphBuilder
import com.flaringapp.ligretto.android.ui.feature.home.HomeDestination
import com.flaringapp.ligretto.android.ui.feature.home.HomeScreen
import com.flaringapp.ligretto.android.ui.utils.navigation.composable

fun NavGraphBuilder.appNavGraph() {
    composable(HomeDestination) {
        HomeScreen()
    }
}
