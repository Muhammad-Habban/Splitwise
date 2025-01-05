package com.example.splitwise.DataAccess

import com.example.splitwise.DataAccess.AppDatabase
import android.content.Context
import androidx.room.Room

object DatabaseInstance {
    @Volatile
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (instance == null) {
            synchronized(AppDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "splitwise_database"
                ).build()
            }
        }
        return instance!!
    }
}
