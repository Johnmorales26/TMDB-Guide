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
import com.johndev.tmdb_guide.common.entities.Seasons
import com.johndev.tmdb_guide.databinding.ItemCardBinding
import com.johndev.tmdb_guide.databinding.ItemSeasonsBinding
import com.johndev.tmdb_guide.getImage

class SeasonsAdapter(var seasonsList: MutableList<Seasons>) : RecyclerView.Adapter<SeasonsAdapter.ViewHolder>(){

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_seasons, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeasonsAdapter.ViewHolder, position: Int) {
        val season = seasonsList[position]
        with(holder) {
            binding.apply {
                tvEpisodeCount.text = "Episode Count: ${season.episode_count}"
                tvSeasonNumber.text = "Season Number: ${season.season_number}"
                tvName.text = "Name: ${season.name}"
                tvAirDate.text = "Air Date: ${season.air_date}"
                Glide
                    .with(context)
                    .load(getImage(season.poster_path.toString().trim()))
                    .centerCrop()
                    .placeholder(R.drawable.ic_cloud_download)
                    .into(imgPoster)
            }
        }
    }

    override fun getItemCount(): Int = seasonsList.size

    fun add(season: Seasons) {
        seasonsList.add(season)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemSeasonsBinding.bind(view)
    }

}