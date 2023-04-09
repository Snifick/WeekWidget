package com.example.letstry.BottomNavigation

import com.example.letstry.R

open class BottomItem(val title:String, val iconId:Int, val route:String){
    object Screen1:BottomItem("Главное", R.drawable.baseline_home_24, "screen_1")
    object Screen2:BottomItem("Настройки", R.drawable.baseline_menu_24, "screen_2")
    object Screen3:BottomItem("О приложении", R.drawable.baseline_info_24, "screen_3")

}