package com.pefdneves.panicmode

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.viewinterop.AndroidView
import com.pefdneves.ui.common.Screen
import com.pefdneves.panicmode.ui.theme.PanicModeTheme
import com.pefdneves.ui.actions.databinding.FragmentActionsBinding
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
    val onNavigate = { dest: String -> navigationController.navigate(dest) }
    NavHost(
        navController = navigationController,
        startDestination = Screen.Splash.route,
    ) {
        composable(Screen.Splash.route) {
            Splash(onNavigate)
        }
        composable(Screen.Wizard.route) {
            Wizard(onNavigate)
        }
        composable(Screen.Actions.route) {
            AndroidView( { context ->
                FragmentActionsBinding.inflate(LayoutInflater.from(context)).root
            })
        }
    }
}

