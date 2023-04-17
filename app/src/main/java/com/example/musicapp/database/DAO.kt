package com.example.musicapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.musicapp.model.MusicFavorite
@Dao
interface DAO{
    @Insert
    suspend fun insert(musicFavorite: MusicFavorite)
    @Delete
    suspend fun delete(musicFavorite: MusicFavorite)
    @Query("SELECT * FROM music ")
    fun getAll() : List<MusicFavorite>
    @Query("SELECT * FROM music WHERE name =:name")
    fun getByName(name : String) : MusicFavorite
    @Query("DELETE FROM music WHERE name =:name")
    suspend fun deleteByName(name: String)
}