package com.tarekrefaei.littlelemonstore

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screens(val route: String) {
    object HomeScreen : Screens("Home_Screen")
    object ProfileScreen : Screens("Profile_Screen")
    object OnBoardingScreen : Screens("OnBoarding_Screen")
}

@Composable
fun Destinations(
    navController: NavHostController,
    sharedPreferences: SharedPreferences,
    hasData: Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = if (hasData) {
            Screens.ProfileScreen.route
        } else {
            Screens.OnBoardingScreen.route
        }
    ) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screens.ProfileScreen.route) {
            ProfileScreen(navController, sharedPreferences = sharedPreferences)
        }
        composable(route = Screens.OnBoardingScreen.route) {
            OnBoarding(navController, sharedPreferences = sharedPreferences)
        }
    }
}
