package com.example.musicapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
 class MusicFavorite(    var name : String , var single : String, var image : Int, var sourceMusic : Int) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0;
}