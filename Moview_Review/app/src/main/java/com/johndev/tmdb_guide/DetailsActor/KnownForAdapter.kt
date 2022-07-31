package com.johndev.tmdb_guide.DetailsActor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.johndev.tmdb_guide.Actors.KnownFor
import com.johndev.tmdb_guide.Interfaces.OnPressedMovie
import com.johndev.tmdb_guide.Provider.Services.Resources
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.ItemCardMovieBinding

class KnownForAdapter(private val movieList: List<KnownFor?>?, val listener: OnPressedMovie):
    RecyclerView.Adapter<KnownForAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_card_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList?.get(position)
        with(holder) {
            binding.apply {
                if (movie != null) {
                    setListener(movie)
                }
                val resources = Resources()
                val imagePosterPath = resources.getImageResource(movie?.poster_path.toString())
                if (movie != null) {
                    tvTitle.text = movie.title.toString().trim()
                }
                imgMovie.load(imagePosterPath) {
                    crossfade(true)
                    scale(Scale.FILL)
                    placeholder(R.drawable.ic_broken_image)
                }
            }
        }
    }

    override fun getItemCount(): Int = movieList!!.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemCardMovieBinding.bind(view)

        fun setListener(movie: KnownFor){
            binding.root.setOnClickListener {
                listener.OnMovieKnowPressed(movie)
            }
        }

    }

}