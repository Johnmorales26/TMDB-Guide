package com.johndev.mbooking.presentation.navigation

sealed class Routes(val route: String) {

    data object  LoginScreen : Routes("LoginScreen")
    data object HomeScreen : Routes("HomeScreen")
    data object  DetailsMovieScreen : Routes("DetailsMovieScreen/{movieId}") {
        fun createRoute(movieId: Long) = "DetailsMovieScreen/$movieId"
    }

}