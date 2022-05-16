package com.pefdneves.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.pefdneves.ui.common.Screen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue

@Composable
fun Splash(navController: NavHostController) {
    Splash(
        hiltViewModel(),
        navController
    )
}

@Composable
internal fun Splash(viewModel: SplashViewModel, navController: NavHostController) {
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
        navController.navigate(Screen.Wizard.route)
    }
}
