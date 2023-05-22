package com.example.letstry.BottomNavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "screen_2") {
        composable("screen_2") {

         Screen2()
        }
        composable("screen_3") {

            Screen3()
        }
    }
}