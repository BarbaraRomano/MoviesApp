package com.example.appfilmes.view

import android.annotation.SuppressLint
import com.example.appfilmes.adapters.CategoryAdapter
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfilmes.API.api
import com.example.appfilmes.R
import com.example.appfilmes.databinding.ActivityMainScreenBinding
import com.example.appfilmes.model.Categories
import com.example.appfilmes.model.Category
import com.example.appfilmes.model.Movie
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private var categoryList: MutableList<Category> = mutableListOf()
    private var movieList: MutableList<Movie> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#FF000000")

        val recyclerViewMovies = binding.recyclerViewMovies
        recyclerViewMovies.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter(this, categoryList)
        recyclerViewMovies.setHasFixedSize(true)
        recyclerViewMovies.adapter = categoryAdapter

        binding.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
        }
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stackmobile.com.br/")
            .build()
            .create(api::class.java)

        retrofit.categoryList().enqueue(object : Callback<Categories> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                if (response.code() == 200) {
                    response.body()?.let {
                        categoryAdapter.categoryList.addAll(it.categories)
                        categoryAdapter.notifyDataSetChanged()
                        binding.progressBar.visibility = View.GONE
                        binding.txtLoading.visibility= View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                Toast.makeText(applicationContext,"Erro ao buscar todos os filmes!", Toast.LENGTH_SHORT).show()
            }

        })
    }
}