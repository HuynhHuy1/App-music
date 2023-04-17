package com.example.musicapp.repository

import android.app.Application
import com.example.musicapp.database.AppDatabase
import com.example.musicapp.database.DAO
import com.example.musicapp.model.Model
import com.example.musicapp.model.MusicFavorite

    class listFavorite (app : Application) {
        lateinit var dao : DAO
        init {
            val appDatabase = AppDatabase.getdatabase(app)
            dao = appDatabase.musicDAO()
        }
        suspend fun insert(model : MusicFavorite) = dao.insert(model)

        suspend fun delete(model : MusicFavorite) = dao.delete(model)

        fun getAll() = dao.getAll()

        fun getByName(name : String) = dao.getByName(name)

        suspend fun deleteByName(name: String) = dao.deleteByName(name)
    }