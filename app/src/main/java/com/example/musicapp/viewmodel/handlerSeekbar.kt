package com.example.musicapp.viewmodel

import android.media.MediaPlayer
import android.util.Log
import android.view.View
import android.widget.SeekBar
import com.example.musicapp.R
import java.io.IOException
import java.util.logging.Handler

class handlerSeekbar(var mediaPlayer: MediaPlayer) {
    lateinit var seekbar : SeekBar
    fun handle(view : View){
        if (mediaPlayer != null  ){
            seekbar = view.findViewById(R.id.seekbar)
            var musicTime = mediaPlayer.duration
            var maxProcess = musicTime
            seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                    if(fromUser){
                        mediaPlayer.seekTo(progress)
                        Log.d("TAG", "onProgressChanged: ")
                    }

                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }

            })
        }
    }
    fun handler(view : View, isDestroy : Boolean){
            try {
                seekbar = view.findViewById(R.id.seekbar)
                seekbar.max = mediaPlayer.duration
                val handler1 = android.os.Handler()
                handler1.postDelayed(object : Runnable{
                    override fun run(){
                        if (mediaPlayer.isPlaying()) {
                            seekbar.progress = mediaPlayer.currentPosition
                            handler1.postDelayed(this,100)
                        }
                    }
                },100)
            }
            catch (e : IOException){
                Log.d("TAG", "handler: ")
            }




    }

}