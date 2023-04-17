package com.example.musicapp.view

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.musicapp.R
import com.example.musicapp.database.AppDatabase
import com.example.musicapp.databinding.ActivityMainBinding
import com.example.musicapp.repository.dataList
import com.example.musicapp.repository.listFavorite

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var listFragment = fragment_list()
        var playFragment = fragment_play()
        var favoriteFragment = favorite_fragment()
        supportFragmentManager.beginTransaction().replace(R.id.frapment,listFragment).commit()
        val bottomnav = binding.bottomNavigationView

        bottomnav.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.menuList -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frapment,listFragment).commitNow()
                    var fragment = binding.bottomNavigationView
                    true
                }
                R.id.menuHome -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frapment,playFragment).commitNow()
                    var fragment = binding.bottomNavigationView
                    true
                }
                R.id.favorite_menu -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frapment,favoriteFragment).commitNow()
                    var fragment = binding.bottomNavigationView
                    true
                }
                else -> false
            }
        }
    }
}