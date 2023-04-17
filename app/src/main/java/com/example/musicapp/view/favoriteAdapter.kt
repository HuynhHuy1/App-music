package com.example.musicapp.view

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.musicapp.R
import com.example.musicapp.model.Model
import com.example.musicapp.model.MusicFavorite
import com.example.musicapp.repository.dataList
import org.w3c.dom.Text

class favoriteAdapter(var list : List<MusicFavorite>,var  onClick : handleOnClickItem ) : Adapter<favoriteAdapter.viewHolder>() {
    class viewHolder (view : View) : ViewHolder(view){
        var image = view.findViewById<ImageView>(R.id.image_favorite)
        var name = view.findViewById<TextView>(R.id.tv_name_music_item)
        var single = view.findViewById<TextView>(R.id.tv_single_item_favorite)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_favorite,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.image.setImageResource(list[position].image)
        holder.name.setText(list[position].name)
        holder.single.setText(list[position].single)
        holder.itemView.setOnClickListener {
            onClick.onClickItem(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}