package com.pefdneves.panicmode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.pefdneves.ui.common.Screen
import com.pefdneves.panicmode.ui.theme.PanicModeTheme
import com.pefdneves.ui.splash.Splash
import com.pefdneves.ui.wizard.Wizard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PanicModeTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navigationController by rememberUpdatedState(newValue = rememberNavController())
    NavHost(
        navController = navigationController,
        startDestination = Screen.Splash.route,
    ) {
        composable(Screen.Splash.route) {
            Splash(navController = navigationController)
        }
        composable(Screen.Wizard.route) {
            Wizard(navController = navigationController)
        }
    }
}

