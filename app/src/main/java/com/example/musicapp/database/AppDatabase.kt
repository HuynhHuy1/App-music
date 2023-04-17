package com.example.musicapp.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.musicapp.model.Model
import com.example.musicapp.model.MusicFavorite

@Database(entities = [MusicFavorite::class], version = 1)
abstract class AppDatabase() : RoomDatabase()  {
    abstract fun musicDAO() : DAO
    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null
        fun getdatabase(context : Context ) : AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "music_app"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}