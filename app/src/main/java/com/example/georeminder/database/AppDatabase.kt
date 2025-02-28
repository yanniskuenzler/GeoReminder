package com.example.georeminder.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class],
    version = 3,
    exportSchema = true,

    autoMigrations = [
        AutoMigration (from = 2, to = 3)
    ]

)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    /**
    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance != null) {
                return instance!!
            }

            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "task-database"
                    ).build()
                }
                return instance!!
            }
        }
    }
    */


}