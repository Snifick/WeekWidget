package com.example.letstry.BottomNavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
            bottomBar = { BottomNavigation(navController = navController)}
    ) {

            Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
                NavGraph(navHostController = navController)
            }



    }
}