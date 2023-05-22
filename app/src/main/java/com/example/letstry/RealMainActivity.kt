package com.example.letstry

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Intent
import android.content.pm.ActivityInfo.WindowLayout
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ContentInfoCompat.Flags
import androidx.core.view.WindowCompat
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager

import com.example.letstry.BottomNavigation.MainScreen
import com.example.letstry.ui.theme.*
import com.google.android.material.divider.MaterialDivider
import java.time.Duration
import java.util.*

class RealMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val workManager = WorkManager.getInstance(this)
        val periodicWorkRequest =     PeriodicWorkRequestBuilder<MyWorker>(Duration.ofMinutes(30)).build()
        workManager.enqueue(periodicWorkRequest)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.newclrBot)

        setContent {
            MaterialTheme {


                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                ) {

                    getWeek(LocalContext.current)

                    MainScreen()
                }

            }
        }
    }
}
