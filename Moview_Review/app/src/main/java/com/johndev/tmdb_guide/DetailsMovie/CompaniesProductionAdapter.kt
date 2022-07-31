package com.johndev.tmdb_guide.DetailsMovie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.johndev.tmdb_guide.Movies.ProductionCompanies
import com.johndev.tmdb_guide.Provider.Services.Resources
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.ItemCompaniesProductionBinding

class CompaniesProductionAdapter(private val companiesList: List<ProductionCompanies?>?):
    RecyclerView.Adapter<CompaniesProductionAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_companies_production, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val companie = companiesList?.get(position)
        val resources = Resources()
        val imagePosterPath = resources.getImageResource(companie?.logo_path.toString())
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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemCompaniesProductionBinding.bind(view)
    }

}