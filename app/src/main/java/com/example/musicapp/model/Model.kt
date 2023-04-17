package com.example.musicapp.model

class Model
    (var srcMusic : Int,
     var name : String,
     var single : String,
     var srcPlayMucsic : Int,
     var isFavorite: Boolean = false
    )
{
        fun toggleFavorite(){
            isFavorite = !isFavorite
        }
    }
