package com.johndev.tmdb_guide.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johndev.tmdb_guide.common.entities.Genre
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.ItemGenresMovieBinding

class GenresMovieAdapter(private val genresList: List<Genre?>?):
    RecyclerView.Adapter<GenresMovieAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_genres_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genresList?.get(position)
        with(holder) {
            binding.apply {
                genre?.apply {
                    cpGenres.text = genre.name.toString().trim()
                }
            }
        }
    }

    override fun getItemCount(): Int = genresList!!.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemGenresMovieBinding.bind(view)
    }

}