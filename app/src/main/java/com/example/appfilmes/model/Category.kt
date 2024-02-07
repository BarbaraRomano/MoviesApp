package com.example.appfilmes.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("titulo") val title: String? = null,
    @SerializedName("capas") val movies: MutableList<Movie> = mutableListOf()
)

data class Movie(
    @SerializedName("url_imagem") val cover: String? = null,
    @SerializedName("id") val id: Int = 0,
    val nome: String? = null,
    val descricao: String? = null,
    val elenco: String? = null,
)

data class Categories (@SerializedName("categoria")
   val categories: MutableList<Category> = mutableListOf()
)