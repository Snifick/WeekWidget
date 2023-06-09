package com.example.letstry

import android.app.PendingIntent
import java.util.Calendar;
import java.util.Date;
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.IntentSender
import android.content.SharedPreferences
import android.os.strictmode.IntentReceiverLeakedViolation
import android.text.BoringLayout
import android.util.Log
import android.widget.RemoteViews
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.flow.combine
import java.text.DateFormat.getTimeInstance
import java.text.SimpleDateFormat


const val SYNC_CLICKED = "WidgetTextClicked"
var f: Int = 0
class Widget : AppWidgetProvider() {


    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        for (appWidgetId in appWidgetIds) {
            val views: RemoteViews = RemoteViews(
                context.packageName,
                R.layout.widget
            ).apply {
                setOnClickPendingIntent(R.id.linLayout, getPendingSelfIntent(context, SYNC_CLICKED))
            }

            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (SYNC_CLICKED == intent.action || intent.action == AppWidgetManager.ACTION_APPWIDGET_ENABLED||intent.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {

            val remoteViews = RemoteViews(context.packageName, R.layout.widget)
            val preferences = MyPref(context)
            remoteViews.setInt(
                R.id.linLayout,
                "setBackgroundResource",
                preferences.backID!!
            )
            val component = ComponentName(context, Widget::class.java)
            val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")


            val currentDate = sdf.format(Date())
            val appWidgetManager = AppWidgetManager.getInstance(context)
            remoteViews.setTextViewText(R.id.nochety, "По счету: ${getWeekValue(context)}")
            remoteViews.setTextViewText(R.id.appwidget_text, "${getWeek(context)}")
            remoteViews.setTextViewText(R.id.appwidget_tex3, "Обновлено: $currentDate")

            if(preferences.mutableBackIsSelected == true){
                f++
                if (f > 5)
                    f = 0

                when (f) {
                    0 -> remoteViews.setInt(
                        R.id.linLayout,
                        "setBackgroundResource",
                        R.drawable.app_widget_background_mutable1
                    )
                    1 -> remoteViews.setInt(
                        R.id.linLayout,
                        "setBackgroundResource",
                        R.drawable.app_widget_background_mutable2
                    )
                    2 -> remoteViews.setInt(
                        R.id.linLayout,
                        "setBackgroundResource",
                        R.drawable.app_widget_background_mutable3
                    )
                    3 -> remoteViews.setInt(
                        R.id.linLayout,
                        "setBackgroundResource",
                        R.drawable.app_widget_background_mutable4
                    )
                    4 -> remoteViews.setInt(
                        R.id.linLayout,
                        "setBackgroundResource",
                        R.drawable.app_widget_background_mutable5
                    )
                    5 -> remoteViews.setInt(
                        R.id.linLayout,
                        "setBackgroundResource",
                        R.drawable.app_widget_background_mutable6
                    )


                }
            }
            appWidgetManager.updateAppWidget(component, remoteViews)
        }
    }

    override fun onEnabled(context: Context) {
      onReceive(context, Intent(SYNC_CLICKED))
    }

    override fun onDisabled(context: Context) {

    }
}

fun getWeek(context: Context): String {

    val preferences = MyPref(context)
    getWeekValue(context)

    if (preferences.weekPref == null) {
        preferences.oddWeek = "?"
    } else {

        if(preferences.inOrderStartFrom!! % 2 == preferences.inOrder!! %2)
            preferences.weekPref = preferences.oddWeek
        else
        {
            if(preferences.oddWeek == "Верхняя")
                preferences.weekPref = "Нижняя"
            else
                preferences.weekPref = "Верхняя"
        }


    }


    return preferences.weekPref.toString().uppercase()

}

fun getWeekValue(context: Context): Int {


    val preferences = MyPref(context)

    val calendar = Calendar.getInstance()

    val different = preferences.inOrderWeekOfYearBegin!! - preferences.inOrderStartFrom!!

    preferences.inOrder = calendar.get(Calendar.WEEK_OF_YEAR) - different
    if (preferences.inOrderWeekOfYearBegin == -1) {
        preferences.inOrder = 0
    }


    return preferences.inOrder!!

}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {


    val views = RemoteViews(context.packageName, R.layout.widget)


    val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")

    val currentDate = sdf.format(Date())


    val appWidgetManager = AppWidgetManager.getInstance(context)
    views.setTextViewText(R.id.nochety, "По счету: ${getWeekValue(context)}")
    views.setTextViewText(R.id.appwidget_text, "${getWeek(context)}")


    views.setTextViewText(R.id.appwidget_tex3, "Последнее обновление: $currentDate")
    views.setOnClickPendingIntent(R.id.linLayout, getPendingSelfIntent(context, SYNC_CLICKED))


    appWidgetManager.updateAppWidget(appWidgetId, views)
}


fun getPendingSelfIntent(context: Context, action: String?): PendingIntent {


    val intent = Intent(context, Widget::class.java)
    intent.action = action
    return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

}

