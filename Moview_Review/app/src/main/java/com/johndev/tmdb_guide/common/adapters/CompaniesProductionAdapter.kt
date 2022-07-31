package com.johndev.tmdb_guide.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.johndev.tmdb_guide.common.entities.ProductionCompanies
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.entities.SpokenLanguages
import com.johndev.tmdb_guide.common.utils.getImageResource
import com.johndev.tmdb_guide.databinding.ItemCompaniesProductionBinding

class CompaniesProductionAdapter(private val companiesList: MutableList<ProductionCompanies>):
    RecyclerView.Adapter<CompaniesProductionAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_companies_production, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val companie = companiesList?.get(position)
        val imagePosterPath = getImageResource(companie?.logo_path.toString())
        with(holder) {
            binding.apply {
                tvName.text = companie?.name.toString().trim()
                tvOrigin.text = companie?.origin_country.toString().trim()
                imgLogo.load(imagePosterPath) {
                    crossfade(true)
                    scale(Scale.FILL)
                    placeholder(R.drawable.ic_broken_image)
                }
            }
        }
    }

    override fun getItemCount(): Int = companiesList!!.size

    fun add(productionCompanies: ProductionCompanies) {
        if (!companiesList!!.contains(productionCompanies)){
            companiesList.add(productionCompanies)
            notifyItemInserted(companiesList.size - 1)
        } else{
            update(productionCompanies)
        }
    }

    private fun update(productionCompanies: ProductionCompanies){
        val index = companiesList!!.indexOf(productionCompanies)
        if (index != -1){
            companiesList[index] = productionCompanies
            notifyItemChanged(index)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemCompaniesProductionBinding.bind(view)
    }

}