package com.johndev.tmdb_guide.TV.DetailsTV

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.utils.getImageResource
import com.johndev.tmdb_guide.databinding.ItemEpisodeBinding

class EpisodeAdapter(private val episodeList: List<Episode>):
    RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_episode, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episode = episodeList[position]
        with(holder) {
            binding.let {
                val imagePosterPath = getImageResource(episode.still_path.toString())
                it.imgEpisode.load(imagePosterPath) {
                    crossfade(true)
                    scale(Scale.FIT)
                    placeholder(R.drawable.ic_broken_image)
                }
                it.tvEpisodeNumber.text = context.getString(R.string.label_episode, episode.episode_number.toString().trim())
                it.tvNameEpisode.text = episode.name.toString().trim()
                it.tvOverview.text = episode.overview.toString().trim()
                it.tvSeasonNumber.text = context.getString(R.string.label_season, episode.season_number.toString().trim())
            }
        }
    }

    override fun getItemCount(): Int = episodeList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemEpisodeBinding.bind(view)

    }

}