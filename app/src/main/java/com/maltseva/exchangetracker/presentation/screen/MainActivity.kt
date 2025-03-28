package com.maltseva.exchangetracker.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationStack()
        }
    }

    @Composable
    fun NavigationStack() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = RouteItem.Home.route) {
            composable(route = RouteItem.Home.route) {
                HomeScreen(
                    onAddCurrencyClick = { navController.navigate(RouteItem.AddCurrency.route) })
            }
            composable(
                route = RouteItem.AddCurrency.route,
            ) {
                AddCurrencyScreen(
                    onBackPressed = { navController.popBackStack() }
                )
            }
        }
    }
}

