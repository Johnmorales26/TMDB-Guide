package com.johndev.tmdb_guide.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johndev.tmdb_guide.common.entities.SpokenLanguages
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.entities.ActorEntity
import com.johndev.tmdb_guide.databinding.ItemSpokenLanguajesBinding

class SpokenLanguajesAdapter(private val languajesList: MutableList<SpokenLanguages>):
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

    fun add(spokenLanguages: SpokenLanguages) {
        if (!languajesList!!.contains(spokenLanguages)){
            languajesList.add(spokenLanguages)
            notifyItemInserted(languajesList.size - 1)
        } else{
            update(spokenLanguages)
        }
    }

    private fun update(spokenLanguages: SpokenLanguages){
        val index = languajesList!!.indexOf(spokenLanguages)
        if (index != -1){
            languajesList[index] = spokenLanguages
            notifyItemChanged(index)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemSpokenLanguajesBinding.bind(view)
    }

}