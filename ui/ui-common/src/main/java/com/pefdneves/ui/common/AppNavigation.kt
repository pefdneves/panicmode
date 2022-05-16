package com.pefdneves.ui.common

sealed class Screen(val route: String) {
    object Wizard : Screen("wizard")
    object Actions : Screen("actions")
    object Splash : Screen("splash")
}
