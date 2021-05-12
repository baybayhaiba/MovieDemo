package com.example.kotlinmvvm_7.persistent

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinmvvm_7.model.Result


@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object {
        @Volatile
        var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MovieDatabase::class.java, "movie_database_data"
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}