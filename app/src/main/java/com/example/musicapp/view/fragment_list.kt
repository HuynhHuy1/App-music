package com.example.musicapp.view

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.ColorSpace.Model
import android.os.Bundle
import android.util.Log
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.musicapp.R
import com.example.musicapp.database.AppDatabase
import com.example.musicapp.model.MusicFavorite
import com.example.musicapp.repository.dataList
import com.example.musicapp.repository.listFavorite
import com.example.musicapp.viewmodel.ListViewModel

class fragment_list : Fragment() {
    lateinit var adapter1: listItemAdapter
    lateinit var fillerList : ArrayList<com.example.musicapp.model.Model>
    lateinit var rcv : RecyclerView
    lateinit var appDatabase: listFavorite
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var listData = dataList().loadData()
        fillerList = ArrayList(listData)
        var view = inflater.inflate(R.layout.fragment_list,container,false)
        var ListViewModel = ListViewModel()
        var play_fragment = fragment_play()
        var application = view.context.applicationContext as Application
        appDatabase = listFavorite(application)
        adapter1 = listItemAdapter(fillerList,ListViewModel.onCreateHandleOnClick(view.context,play_fragment,listData),appDatabase)
        adapter1.notifyDataSetChanged()
        rcv = view.findViewById<RecyclerView>(R.id.recycle_list)
        rcv.adapter = adapter1
        rcv.layoutManager = LinearLayoutManager(context)
        handleSearchView(view, ListViewModel.onCreateHandleOnClick(view.context, play_fragment, listData))
        return view
    }
        fun handleSearchView (view : View, onClickItem: handleOnClickItem){
            var searchView = view.findViewById<android.widget.SearchView>(R.id.sv_list)
            searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    fillerList.clear()
                    for(item in dataList().loadData()){
                        if(item.name.toLowerCase().contains(newText!!.toLowerCase())){
                            fillerList.add(item)
                        }
                    }
                    adapter1 = listItemAdapter(fillerList, onClickItem,appDatabase)
                    rcv.adapter = adapter1
                    return true
                }
            })
        }
}