package com.example.musicapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    lateinit var binding : ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonIntro.setOnClickListener{
            var intent = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}