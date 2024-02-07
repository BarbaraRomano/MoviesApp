package com.example.appfilmes.API

import com.example.appfilmes.model.Categories
import retrofit2.Call
import retrofit2.http.GET

interface api {
    @GET("/filmes")
    fun categoryList(): Call<Categories>
}