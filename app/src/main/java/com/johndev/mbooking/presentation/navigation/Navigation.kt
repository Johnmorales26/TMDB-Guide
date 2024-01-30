package com.johndev.mbooking.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.johndev.mbooking.presentation.screens.home.HomeScreen
import com.johndev.mbooking.presentation.screens.login.LoginScreen
import com.johndev.mbooking.presentation.viewmodels.HomeViewModel
import com.johndev.mbooking.presentation.viewmodels.LoginViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LoginScreen.route) {
        composable(Routes.LoginScreen.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(viewmodel = viewModel)
        }
        composable(Routes.HomeScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewmodel = viewModel)
        }
    }
}