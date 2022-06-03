package com.pefdneves.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pefdneves.ui.common.R as UiCommonR

@Composable
fun Splash(onNavigate: (Int) -> Unit) {
    Splash(
        hiltViewModel(),
        onNavigate
    )
}

@Composable
internal fun Splash(viewModel: SplashViewModel, onNavigate: (Int) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loading")
            CircularProgressIndicator()
        }
    }
    viewModel.isFirstRun.value?.getContentIfNotHandled()?.also {
        onNavigate(UiCommonR.id.action_splash_to_wizard)
    }
}
