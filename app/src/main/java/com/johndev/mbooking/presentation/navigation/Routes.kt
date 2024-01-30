package com.johndev.mbooking.presentation.navigation

sealed class Routes(val route: String) {

    object LoginScreen : Routes("LoginScreen")
    object HomeScreen : Routes("HomeScreen")

}