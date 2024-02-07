package com.example.appfilmes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appfilmes.databinding.CategoryItemBinding
import com.example.appfilmes.model.Category
import com.example.appfilmes.model.Movie


class CategoryAdapter(
    private val context: Context,
    val categoryList: MutableList<Category>,
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemList = CategoryItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoryViewHolder(itemList)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.title.text = categoryList[position].title
        val category = categoryList[position]
        holder.recyclerView.adapter = MoviesAdapter(context, category.movies)
        holder.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
    inner class CategoryViewHolder(binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root){
        val title = binding.CategoryTitle
        val recyclerView = binding.recyclerMovie
    }
}

