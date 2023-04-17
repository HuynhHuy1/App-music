    package com.example.musicapp.view

    import android.annotation.SuppressLint
    import android.content.Context
    import android.media.Image
    import android.media.MediaPlayer
    import android.net.Uri
    import android.os.Bundle
    import android.util.Log
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.Button
    import android.widget.ImageButton
    import android.widget.ImageView
    import android.widget.SeekBar
    import android.widget.TextView
    import androidx.appcompat.widget.SearchView
    import androidx.core.os.bundleOf
    import androidx.fragment.app.Fragment
    import com.example.musicapp.R
    import com.example.musicapp.model.Model
    import com.example.musicapp.repository.dataList
    import com.example.musicapp.viewmodel.SongViewModel
    import com.example.musicapp.viewmodel.handlerSeekbar
    import java.io.File
    import java.io.FileOutputStream
    import java.io.IOException

    class fragment_play : Fragment() {
        var pos : Int = 0
        lateinit var songViewModel : SongViewModel
        var Nextposition : Int = 0
        var Prevposition : Int = 0
        lateinit var model : Model
        lateinit var searchView : SearchView
        @SuppressLint("SuspiciousIndentation")
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            var view = inflater.inflate(R.layout.fragment_play, container, false)
                setBundle(view)
                pos = requireArguments().getInt("srcMusic")
                Nextposition = requireArguments().getInt("position")
                Prevposition = requireArguments().getInt("Prevposition")
                songViewModel = SongViewModel(requireContext(),view)
            return view
        }
        @SuppressLint("SuspiciousIndentation")
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            Log.d("TAG", "${pos}")
            songViewModel.playMusic(pos)

            // next button
            var nextButton = view.findViewById<ImageButton>(R.id.nexticon)
            var listModel = dataList().loadData()

            nextButton.setOnClickListener {
                Nextposition = Nextposition + 1
                if(Nextposition >= listModel.size){
                    Nextposition = 0
                }
                model = listModel[Nextposition]
                songViewModel.nextMusic(view,model)

                Log.d("TAG", "${Nextposition}")

            }

            // prev Button
            var prevButton = view.findViewById<ImageButton>(R.id.prevButton)
                prevButton.setOnClickListener {
               Nextposition = Nextposition - 1
                if(Nextposition < 0){
                    Nextposition = listModel.size - 1
                }
                model = listModel[Nextposition]
                songViewModel.nextMusic(view,model)

                Log.d("TAG", "${Nextposition}")

            }

        }

        override fun onDestroy() {
            super.onDestroy()
            songViewModel.destroyMusic()
        }
            @SuppressLint("UseRequireInsteadOfGet")
        fun setBundle(view : View){
                var bundle : Bundle = arguments !!
                view.findViewById<ImageView>(R.id.image_playing).setImageResource(bundle.getInt("image"))
                view.findViewById<TextView>(R.id.tv_single_play).setText(bundle.getString("single"))
                view.findViewById<TextView>(R.id.name).setText(bundle.getString("name"))
        }

    }