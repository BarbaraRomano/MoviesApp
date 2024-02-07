package com.example.appfilmes.view

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.appfilmes.R
import com.example.appfilmes.databinding.ActivityMovieDetailsBinding
import com.example.appfilmes.databinding.CategoryItemBinding

class MovieDetails : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#000000")

        val cover = intent.extras?.getString("cover")
        val name = intent.extras?.getString("name")
        val description = intent.extras?.getString("description")
        val cast = intent.extras?.getString("cast")

        Glide.with(this).load(cover).centerCrop().into(binding.movieCover)
        binding.movieTitle.setText(name)
        binding.txtDescription.setText("Descrição: $description")
        binding.txtCast.setText("Elenco: $cast")

    }
}