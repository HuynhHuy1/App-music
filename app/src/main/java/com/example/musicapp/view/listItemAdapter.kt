package com.example.musicapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.musicapp.R
import com.example.musicapp.database.AppDatabase
import com.example.musicapp.model.Model
import com.example.musicapp.model.MusicFavorite
import com.example.musicapp.repository.listFavorite
import com.example.musicapp.viewmodel.ListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class listItemAdapter(var list : ArrayList<Model>,var  onClick : handleOnClickItem,var appDatabase: listFavorite ) : Adapter<listItemAdapter.viewHolder>() {
    class viewHolder(view : View) : ViewHolder(view){
        var srcMusic = view.findViewById<ImageView>(R.id.image_item)
        var name = view.findViewById<TextView>(R.id.tv_name_music_item)
        var single = view.findViewById<TextView>(R.id.tv_single)
        var heart = view.findViewById<ImageButton>(R.id.cb_item)
        var listViewMode = ListViewModel()
        var view = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_list,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.srcMusic.setImageResource(list[position].srcMusic)
        holder.name.setText(list[position].name)
        holder.single.setText(list[position].single)
        holder.itemView.setOnClickListener {
            onClick.onClickItem(position)
        }
        holder.heart.setOnClickListener{
            var musicFavorite = MusicFavorite(list[position].name,list[position].single,list[position].srcMusic,list[position].srcPlayMucsic)
            if (!list[position].isFavorite) {
                CoroutineScope(Dispatchers.IO).launch {
                    holder.listViewMode.handleLikeMusic(appDatabase,musicFavorite)
                }
                Log.d("TAG", "onBindViewHolder: ")
                holder.heart.setBackgroundResource(R.drawable.heart_fill)
                list[position].toggleFavorite()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    holder.listViewMode.handleCancelLikeMusic(appDatabase,musicFavorite)
                }
                holder.heart.setBackgroundResource(R.drawable.heart_outline)
                list[position].toggleFavorite()
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            var listFavorit = appDatabase.getAll()
            withContext(Dispatchers.IO){
                for(item in listFavorit){
                    if(list[position].name.equals(item.name)){
                        holder.heart.setBackgroundResource(R.drawable.heart_fill)
                    }
                    //sss
                }
            }

        }

    }

}