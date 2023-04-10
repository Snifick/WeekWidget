package com.example.letstry.BottomNavigation

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.letstry.ui.theme.*

@Composable
fun BottomNavigation(navController: NavController){

    val listItems = listOf(
        BottomItem.Screen1,
        BottomItem.Screen2,
        BottomItem.Screen3
    )
    androidx.compose.material.BottomNavigation(backgroundColor = clrDandM(
        clrNight = clrBotNight,
        clrLight = clrBot
    )) {

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoot = backStackEntry?.destination?.route
        listItems.forEach{
            item->
            BottomNavigationItem(selected = currentRoot == item.route, onClick = {
                navController.navigate(item.route)
            },
            icon = {
                Icon(painter = painterResource(id = item.iconId), contentDescription ="Icon")
            },
            label = {
                Text(text = item.title, fontSize = 9.sp, )
            },
            selectedContentColor = clrDandM(clrNight = Purple700 , clrLight = Color.White ),
                unselectedContentColor =  clrDandM(clrNight =Color.DarkGray , clrLight = black )
                )
        }


    }


}