package com.flaringapp.ligretto.android.ui.feature.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.flaringapp.ligretto.android.ui.utils.navigation.ScreenDestinationWithoutArguments

object HomeDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "home"
}

@Composable
fun HomeScreen() {
    Text(text = "Hello Home")
}
