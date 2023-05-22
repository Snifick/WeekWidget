package com.example.letstry

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val widget = Widget()
        Log.d("QWERTY", "workMAnager have ben updated")
        widget.onReceive(applicationContext, Intent("WidgetTextClicked"))
        return Result.success()
    }
}