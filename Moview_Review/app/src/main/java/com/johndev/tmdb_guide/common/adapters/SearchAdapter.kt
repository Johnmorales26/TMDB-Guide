package com.johndev.tmdb_guide.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.johndev.tmdb_guide.Interfaces.OnPressedSearch
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.entities.SearchData
import com.johndev.tmdb_guide.common.utils.getImageResource
import com.johndev.tmdb_guide.databinding.ItemSearchCardBinding

class SearchAdapter(private var searchList: MutableList<SearchData>, val listener: OnPressedSearch):
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_search_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchData = searchList[position]
        with(holder) {
            binding.let { 
                setListener(searchData, it.imgLogo, it.tvName)
                it.imgLogo.load(getImageResource(searchData.posterPath.toString().trim()))
                it.tvName.text = searchData.name.toString().trim()
                it.tvOriginCountry.text = searchData.description.toString().trim()
            }
        }
    }

    fun add(company: SearchData){
        if (!searchList.contains(company)){
            searchList.add(company)
            notifyItemInserted(searchList.size - 1)
        } else{
            update(company)
        }
    }

    private fun update(company: SearchData){
        val index = searchList.indexOf(company)
        if (index != -1){
            searchList[index] = company
            notifyItemChanged(index)
        }
    }

    fun deleteAll() {
        searchList = mutableListOf()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = searchList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemSearchCardBinding.bind(view)

        fun setListener(searchData: SearchData, imgView: View, tvName: View){
            binding.root.setOnClickListener {
                listener.onSearchPressed(searchData, imgView, tvName)
            }
        }
        
    }
    
}