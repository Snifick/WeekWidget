package com.example.letstry

import android.content.Context
import android.text.BoringLayout

class MyPref(context: Context) {
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    var mutableBackIsSelected: Boolean?
        get() = sharedPreferences.getBoolean("mutableBackIsSelected", false)
        set(value) = sharedPreferences.edit().putBoolean("mutableBackIsSelected", value?:false).apply()

    var backID: Int?
        get() = sharedPreferences.getInt("backId", R.drawable.app_widget_background6)
        set(value) = sharedPreferences.edit().putInt("backId", value?:0).apply()

    var clrBegin: Int?
        get() = sharedPreferences.getInt("clrBegin", 0xFF6E7ED6.toInt())
        set(value) = sharedPreferences.edit().putInt("clrBegin", value?:0).apply()

    var clrEnd: Int?
        get() = sharedPreferences.getInt("clrEnd", 0xFFC04992.toInt())
        set(value) = sharedPreferences.edit().putInt("clrEnd", value?:0).apply()


    var inOrderWeekOfYearBegin: Int?
        get() = sharedPreferences.getInt("inOrderWeekStart", -1)
        set(value) = sharedPreferences.edit().putInt("inOrderWeekStart", value?:0).apply()

    var inOrderStartFrom: Int?
        get() = sharedPreferences.getInt("inOrderStart", -1)
        set(value) = sharedPreferences.edit().putInt("inOrderStart", value?:0).apply()

    var inOrder: Int?
        get() = sharedPreferences.getInt("inOrder", 0)
        set(value) = sharedPreferences.edit().putInt("inOrder", value?:0).apply()

    var weekPref: String?
        get() = sharedPreferences.getString("week", null)
        set(value) = sharedPreferences.edit().putString("week", value).apply()

    var oddWeek:String?
    get() = sharedPreferences.getString("mainWeek",null)
    set(value) = sharedPreferences.edit().putString("mainWeek",value).apply()

}