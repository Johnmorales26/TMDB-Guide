package com.johndev.tmdb_guide.DetailsMovie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johndev.tmdb_guide.Movies.SpokenLanguages
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.ItemSpokenLanguajesBinding

class SpokenLanguajesAdapter(private val languajesList: List<SpokenLanguages?>?):
    RecyclerView.Adapter<SpokenLanguajesAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_spoken_languajes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val languaje = languajesList?.get(position)
        with(holder) {
            binding.apply {
                tvEnglishName.text = languaje?.english_name.toString().trim()
                tvName.text = languaje?.name.toString().trim()
            }
        }
    }

    override fun getItemCount(): Int = languajesList!!.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemSpokenLanguajesBinding.bind(view)
    }

}