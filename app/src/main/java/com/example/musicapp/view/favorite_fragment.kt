package com.example.musicapp.view

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.model.MusicFavorite
import com.example.musicapp.repository.dataList
import com.example.musicapp.repository.listFavorite
import com.example.musicapp.viewmodel.ListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class favorite_fragment : Fragment() {
    var listFavorite : List<MusicFavorite> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_favorite_fragment,container,false)
        var ListViewModel = ListViewModel()
        var play_fragment = fragment_play()
        var listData = dataList().loadData()
        CoroutineScope(Dispatchers.IO).launch {
            listFavorite = listFavorite(view.context.applicationContext as Application).getAll()
            Log.d("TAG", "size: ${listFavorite.size} ")
            withContext(Dispatchers.Main) {
                var adapter1 = favoriteAdapter(listFavorite,ListViewModel.onCreateHandleOnClick(view.context,play_fragment,listData))
                adapter1.notifyDataSetChanged()
                var rcv = view.findViewById<RecyclerView>(R.id.rcv_favorite)
                rcv.adapter = adapter1
                rcv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            }
        }
        return view
    }

}