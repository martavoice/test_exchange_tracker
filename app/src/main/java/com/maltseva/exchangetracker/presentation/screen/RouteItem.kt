package com.maltseva.exchangetracker.presentation.screen

sealed class RouteItem(val route: String) {
    data object Home: RouteItem("home")
    data object AddCurrency: RouteItem("add_currency")
}