package com.johndev.tmdb_guide.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johndev.tmdb_guide.OnVideoListener
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.entities.Videos
import com.johndev.tmdb_guide.databinding.ItemVideosBinding

class VideoAdapter(var videosList: MutableList<Videos>, private val listener: OnVideoListener) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>(){

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_videos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoAdapter.ViewHolder, position: Int) {
        val video = videosList[position]
        with(holder) {
            binding.apply {
                setListener(video)
                tvName.text = "Nombre: " + video.name.toString().trim()
                tvQuality.text = "Calidad: " + video.size.toString().trim()
                tvType.text = "Tipo: " + video.type.toString().trim()
            }
        }
    }

    override fun getItemCount(): Int = videosList.size

    fun add(video: Videos) {
        videosList.add(video)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemVideosBinding.bind(view)

        fun setListener(video: Videos){
            binding.btnPlayVideo.setOnClickListener {
                listener.onClick(video)
            }
        }
    }

}