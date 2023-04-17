package com.example.musicapp.viewmodel

import android.content.Context
import android.media.Image
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import android.util.Log
import android.view.Display.Mode
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.example.musicapp.R
import com.example.musicapp.model.Model
import com.example.musicapp.repository.dataList
import com.example.musicapp.view.fragment_play
import org.w3c.dom.Text
import java.util.ListResourceBundle

class SongViewModel(var context: Context,var  view : View) : ViewModel() {
    private var mediaPlayer: MediaPlayer? = null
    var seekbar: handlerSeekbar? = null
    var isDestroy : Boolean = false

    fun createMediaPlayer(){

    }
    fun playMusic(srcMusic : Int) {
        var button = view.findViewById<ImageButton>(R.id.imageButton)
        var seekBar = view.findViewById<SeekBar>(R.id.seekbar)
        mediaPlayer = MediaPlayer.create(context, srcMusic)
        mediaPlayer!!.start()
        button.setImageResource(R.drawable.play)
        button.setOnClickListener{
            if(!mediaPlayer!!.isPlaying){
                mediaPlayer!!.start()
                button.setImageResource(R.drawable.play)
                handSeekbar(view)
//
            }
            else{
                mediaPlayer!!.pause()
                button.setImageResource(R.drawable.pause_icon)
            }
        }
    }
    fun handSeekbar(view: View){
        if(mediaPlayer != null){
            if(mediaPlayer!!.isPlaying){
                seekbar = handlerSeekbar(mediaPlayer!!)
                seekbar?.handler(view,isDestroy)
                seekbar?.handle(view)
                Log.d("TAG", "handSeekbar: ")
            }
        }

        else{
            Log.d("tag", "media don't playing")
        }
    }
    fun destroyMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }
    override fun onCleared() {
        super.onCleared()
        seekbar = null
        mediaPlayer = null
        isDestroy = false
    }
    fun nextMusic(view : View , model: Model){
        view.findViewById<ImageView>(R.id.image_playing).setImageResource(model.srcMusic)
        view.findViewById<TextView>(R.id.name).setText(model.name)
        view.findViewById<TextView>(R.id.tv_single_play).setText(model.single)
        seekbar = null
        mediaPlayer?.release()
        mediaPlayer = null
        playMusic(model.srcPlayMucsic)
    }
    fun prevMusic(view : View , model: Model){
        view.findViewById<ImageView>(R.id.image_playing).setImageResource(model.srcMusic)
        view.findViewById<TextView>(R.id.name).setText(model.name)
        view.findViewById<TextView>(R.id.tv_single_play).setText(model.single)
        seekbar = null
        mediaPlayer?.release()
        mediaPlayer = null
        playMusic(model.srcPlayMucsic)
    }

}