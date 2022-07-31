package com.johndev.tmdb_guide.TV

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.Interfaces.OnTVPressed
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.ItemCardBinding

class CardTVAdapter(private val tvList: MutableList<TV>, var listener: OnTVPressed):
    RecyclerView.Adapter<CardTVAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tv = tvList[position]
        with(holder) {
            binding.apply {
                setListener(tv)
                imgMovie.load("${Constans.IMAGES_URL}${tv.poster_path}")
            }
        }
    }

    override fun getItemCount(): Int = tvList.size

    fun add(tv: TV){
        if (!tvList.contains(tv)){
            tvList.add(tv)
            notifyItemInserted(tvList.size - 1)
        } else{
            update(tv)
        }
    }

    private fun update(tv: TV){
        val index = tvList.indexOf(tv)
        if (index != -1){
            tvList[index] = tv
            notifyItemChanged(index)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemCardBinding.bind(view)

        fun setListener(tv: TV){
            binding.root.setOnClickListener {
                listener.OnPressedTV(tv)
            }
        }

    }

}