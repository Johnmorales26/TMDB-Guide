package com.johndev.mbooking.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.johndev.mbooking.presentation.screens.detailsMovie.DetailsMovieScreen
import com.johndev.mbooking.presentation.screens.home.HomeScreen
import com.johndev.mbooking.presentation.screens.login.LoginScreen
import com.johndev.mbooking.presentation.viewmodels.DetailsMovieViewModel
import com.johndev.mbooking.presentation.viewmodels.HomeViewModel
import com.johndev.mbooking.presentation.viewmodels.LoginViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LoginScreen.route) {
        composable(Routes.LoginScreen.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(viewModel = viewModel) {
                navController.navigate(Routes.HomeScreen.route) {
                    popUpTo(Routes.LoginScreen.route) { inclusive = true }
                }
            }
        }
        composable(Routes.HomeScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel = viewModel) { movieId ->
                navController.navigate(Routes.DetailsMovieScreen.createRoute(movieId))
            }
        }
        composable(
            Routes.DetailsMovieScreen.route,
            arguments = listOf(navArgument("movieId") {
                type = NavType.LongType
            })
        ) {

            val viewModel = hiltViewModel<DetailsMovieViewModel>()
            DetailsMovieScreen(viewModel = viewModel, movieId = it.arguments?.getLong("movieId"))
        }
    }
}