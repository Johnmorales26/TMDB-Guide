package com.johndev.tmdb_guide.TV.DetailsTV

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.johndev.tmdb_guide.Interfaces.OnPressedSeason
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.utils.getImageResource
import com.johndev.tmdb_guide.databinding.ItemSeasonBinding

class SeasonsAdapter(private val seasonsList: List<Seasons>, var listener: OnPressedSeason):
    RecyclerView.Adapter<SeasonsAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_season, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val season = seasonsList[position]
        with(holder) {
            binding.let {
                setListener(season)
                val imagePosterPath = getImageResource(season.poster_path.toString())
                it.imgPosterSeason.load(imagePosterPath) {
                    crossfade(true)
                    scale(Scale.FILL)
                    placeholder(R.drawable.ic_broken_image)
                }
                it.tvSeason.text = context.getString(R.string.label_season, season.season_number.toString().trim())
            }
        }
    }

    override fun getItemCount(): Int = seasonsList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemSeasonBinding.bind(view)

        fun setListener(season: Seasons){
            binding.root.setOnClickListener {
                listener.onSeasonPressed(season)
            }
        }

    }

}