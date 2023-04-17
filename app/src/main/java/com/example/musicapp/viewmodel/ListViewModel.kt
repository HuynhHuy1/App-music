package com.example.musicapp.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import com.example.musicapp.R
import com.example.musicapp.database.AppDatabase
import com.example.musicapp.model.Model
import com.example.musicapp.model.MusicFavorite
import com.example.musicapp.repository.listFavorite
import com.example.musicapp.view.fragment_play
import com.example.musicapp.view.handleOnClickItem
import com.example.musicapp.view.listItemAdapter
import java.util.Objects

class ListViewModel : ViewModel() {
    lateinit var song : Model
    lateinit var searchView : SearchView
    fun onCreateHandleOnClick (context: Context,fragmentPlay: fragment_play,listData : List<Model>) : handleOnClickItem {
        return object : handleOnClickItem{
            override fun onClickItem(pos: Int) {
                val fragmentMNG = (context as AppCompatActivity).supportFragmentManager
                fragmentMNG.beginTransaction().replace(R.id.frapment,fragmentPlay).commit()
                fragmentMNG.beginTransaction().addToBackStack(null)
                var bundle = Bundle()
                bundle.putInt("image",listData[pos].srcMusic)
                bundle.putString("name",listData[pos].name)
                bundle.putString("single",listData[pos].single)
                bundle.putInt("srcMusic",listData[pos].srcPlayMucsic)
                bundle.putInt("position",pos)
                fragmentPlay.arguments = bundle
            }
        }
    }
    suspend fun handleLikeMusic(appDatabase: listFavorite, model: MusicFavorite){
        val existingMusic = appDatabase.getByName(model.name)
        if(existingMusic == null){
            appDatabase.insert(model)
            Log.d("TAG", "${existingMusic} ")
        }
    }
    suspend fun handleCancelLikeMusic(appDatabase: listFavorite, model: MusicFavorite){
        appDatabase.deleteByName(model.name)
        Log.d("TAG", "handleCancelLikeMusic: ")
    }

}