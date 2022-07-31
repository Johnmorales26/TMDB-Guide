package com.johndev.tmdb_guide.MoviePopularTheaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.johndev.tmdb_guide.Interfaces.OnPressedMovie
import com.johndev.tmdb_guide.PopularMovies.MoviePopular
import com.johndev.tmdb_guide.Provider.Services.Resources
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.ItemCardMovieBinding

class CardMovieAdapter(private val MoviePopularList: MutableList<MoviePopular>, var listener: OnPressedMovie):
    RecyclerView.Adapter<CardMovieAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_card_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = MoviePopularList[position]
        with(holder) {
            binding.apply {
                setListener(movie)
                val resources = Resources()
                val imagePosterPath = resources.getImageResource(movie.poster_path.toString())
                tvTitle.text = movie.title?.trim()
                imgMovie.load(imagePosterPath)
            }
        }
    }

    override fun getItemCount(): Int = MoviePopularList.size

    fun add(movie: MoviePopular){
        if (!MoviePopularList.contains(movie)){
            MoviePopularList.add(movie)
            notifyItemInserted(MoviePopularList.size - 1)
        } else{
            update(movie)
        }
    }

    private fun update(movie: MoviePopular){
        val index = MoviePopularList.indexOf(movie)
        if (index != -1){
            MoviePopularList[index] = movie
            notifyItemChanged(index)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemCardMovieBinding.bind(view)

        fun setListener(movie: MoviePopular){
            binding.root.setOnClickListener {
                listener.OnMoviePressed(movie)
            }
        }

    }

}