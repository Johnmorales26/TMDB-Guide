package com.johndev.tmdb_guide.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.johndev.tmdb_guide.OnPersonListener
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.entities.ResultPerson
import com.johndev.tmdb_guide.databinding.ItemPersonBinding
import com.johndev.tmdb_guide.getImage

class PersonAdapter(var personList: MutableList<ResultPerson>, private val listener: OnPersonListener) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>(){

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = personList[position]
        with(holder) {
            binding.apply {
                setListener(person, imgProfile, tvName)
                imgProfile.load(getImage(person.profile_path.trim())) {
                    crossfade(true)
                    placeholder(R.drawable.ic_cloud_download)
                    transformations(CircleCropTransformation())
                }
                tvName.text = person.name.trim()
                tvRol.text = person.known_for_department.trim()
            }
        }
    }

    override fun getItemCount(): Int = personList.size

    fun add(person: ResultPerson) {
        personList.add(person)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemPersonBinding.bind(view)

        fun setListener(person: ResultPerson, imgPoster: View, tvName: View){
            binding.root.setOnClickListener {
                listener.OnClick(person, imgPoster, tvName)
            }
        }
    }

}