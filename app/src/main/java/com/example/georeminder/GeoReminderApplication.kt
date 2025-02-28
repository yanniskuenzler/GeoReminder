package com.example.georeminder

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class GeoReminderApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "ForegroundServiceChannelId",
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            // service provided by Android Operating system to show notification outside of our app
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}