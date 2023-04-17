package com.example.musicapp.view

import android.graphics.ColorSpace.Model
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.musicapp.R

class albumSingleAdapter(var list : ArrayList<com.example.musicapp.model.Model>, var  onClick : handleOnClickItem ) :
    Adapter<albumSingleAdapter.viewHolder>() {
    class viewHolder (view : View) : RecyclerView.ViewHolder(view){
        var image = view.findViewById<ImageView>(R.id.image_favorite)
        var name = view.findViewById<TextView>(R.id.tv_name_music_item)
        var single = view.findViewById<TextView>(R.id.tv_single_item_favorite)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_list,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.image.setImageResource(list[position].srcMusic)
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