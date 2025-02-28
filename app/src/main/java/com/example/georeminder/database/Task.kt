package com.example.georeminder.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val lat: Float,
    val lon: Float,
    val repeat: Boolean
)