package com.example.appfilmes.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appfilmes.databinding.MovieItemBinding
import com.example.appfilmes.model.Movie
import com.example.appfilmes.view.MovieDetails


class MoviesAdapter(
    private val context: Context,
    private val movieList: MutableList<Movie>
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieList = MovieItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(movieList)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Glide.with(context).load(movieList[position].cover).centerCrop().into(holder.movie)

        holder.movie.setOnClickListener{
            val intent = Intent(context, MovieDetails::class.java)
            intent.putExtra("cover", movieList[position].cover)
            intent.putExtra("name", movieList[position].nome)
            intent.putExtra("description", movieList[position].descricao)
            intent.putExtra("cast", movieList[position].elenco)
            context.startActivity(intent)

        }
    }

    override fun getItemCount() = movieList.size

    inner class MovieViewHolder(binding: MovieItemBinding):RecyclerView.ViewHolder(binding.root){
        val movie = binding.MovieCover
    }
}
