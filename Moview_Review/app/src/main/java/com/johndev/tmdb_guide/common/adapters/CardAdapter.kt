package com.johndev.tmdb_guide.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.johndev.tmdb_guide.OnMovieListener
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.databinding.ItemCardBinding
import com.johndev.tmdb_guide.getImage

class CardAdapter(var moviesList: MutableList<Result>, private val listener: OnMovieListener) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>(){

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]
        with(holder) {
            binding.apply {
                setListener(movie, imgPoster)
                Glide
                    .with(context)
                    .load(getImage(movie.poster_path.toString().trim()))
                    .centerCrop()
                    .placeholder(R.drawable.ic_cloud_download)
                    .into(imgPoster)
            }
        }
    }

    override fun getItemCount(): Int = moviesList.size

    fun add(movie: Result) {
        moviesList.add(movie)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemCardBinding.bind(view)

        fun setListener(movies: Result, imgPoster: View){
            binding.root.setOnClickListener {
                listener.onMovieListener(movies, imgPoster)
                true
            }
        }
    }

}