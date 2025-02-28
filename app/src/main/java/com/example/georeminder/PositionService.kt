package com.example.georeminder

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.Observer

class PositionService : LifecycleService(), LocationListener {

    override fun onCreate() {
        super.onCreate()

        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0f, this)


    }

    // This is triggered when another android component sends an Intent to this running service
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent?.action.equals( Actions.START.toString())) {
            start()
        } else if (intent?.action.equals( Actions.STOP.toString()))
            stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    enum class Actions {
        START, STOP
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, "ForegroundServiceChannelId")
            //.setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Foreground Service")
            .setContentText("Foreground service is running")
            .build()

        // Start the service in the foreground
        startForeground(1, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    */

    override fun onLocationChanged(location: Location) {
        val latitude = location.latitude
        val longitude = location.longitude
        Log.d("DEBUG", latitude.toString())
        Log.d("DEBUG", longitude.toString())

        val taskModel = TaskModel(applicationContext)
        taskModel.fetchTasks()

        taskModel.tasks.observe(this, Observer { tasks ->
            tasks.map {

                if (checkCoordinates(latitude, longitude, it.lat, it.lon)) {
                    Log.d("RESULT", "true")
                    taskModel.deleteTask(it)
                }
            }
        })

    }

    private fun checkCoordinates(currLat: Double, currLon: Double, taskLat: Float, taskLon: Float): Boolean {
        val latDif = currLat - taskLat
        val lonDif = currLon - taskLon

        return (latDif > -0.001 && latDif < 0.001) &&(lonDif > -0.001 && lonDif < 0.001)
    }
}