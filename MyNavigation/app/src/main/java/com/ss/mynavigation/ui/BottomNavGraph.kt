package com.ss.mynavigation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ss.mynavigation.ui.screen.HomeScreen
import com.ss.mynavigation.ui.screen.ProfileScreen
import com.ss.mynavigation.ui.screen.SettingScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
        composable(route = BottomBarScreen.Setting.route) {
            SettingScreen()
        }
    }
}