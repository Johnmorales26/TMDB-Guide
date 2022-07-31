package com.johndev.tmdb_guide.common.adapters

import android.content.Context
import android.icu.number.Scale
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.johndev.tmdb_guide.Interfaces.OnPressedMovie
import com.johndev.tmdb_guide.common.entities.MoviePopular
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.entities.DataRequestEntity
import com.johndev.tmdb_guide.common.utils.getImageResource
import com.johndev.tmdb_guide.databinding.ItemCardMovieBinding

class CardMovieAdapter(private val MoviePopularList: MutableList<DataRequestEntity>, var listener: OnPressedMovie):
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
                setListener(movie, imgMovie)
                /*val imagePosterPath = getImageResource(movie.backdrop_path)
                imgMovie.load(imagePosterPath) {
                    crossfade(true)
                    scale(coil.size.Scale.FILL)
                }*/
                Glide.with(context)
                    .load(getImageResource(movie.backdrop_path))
                    .centerCrop()
                    .into(imgMovie);
            }
        }
    }

    override fun getItemCount(): Int = MoviePopularList.size

    fun add(movie: DataRequestEntity){
        if (!MoviePopularList.contains(movie)){
            MoviePopularList.add(movie)
            notifyItemInserted(MoviePopularList.size - 1)
        } else{
            update(movie)
        }
    }

    private fun update(movie: DataRequestEntity){
        val index = MoviePopularList.indexOf(movie)
        if (index != -1){
            MoviePopularList[index] = movie
            notifyItemChanged(index)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemCardMovieBinding.bind(view)

        fun setListener(movie: DataRequestEntity, imgPhoto: View){
            binding.root.setOnClickListener {
                listener.OnMoviePressed(movie, imgPhoto)
            }
        }

    }

}