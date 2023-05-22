package com.example.letstry.BottomNavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        Box(modifier = Modifier.fillMaxSize()) {
            NavGraph(navHostController = navController)
        }

    }
}
@Composable
fun clrDandM(clrNight:Color, clrLight:Color) : Color{
    if(isSystemInDarkTheme())
    {
       return  clrNight
    } else {
      return   clrLight
    }
}