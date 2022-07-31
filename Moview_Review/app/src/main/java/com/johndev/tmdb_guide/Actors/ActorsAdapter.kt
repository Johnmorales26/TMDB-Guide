package com.johndev.tmdb_guide.Actors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.johndev.tmdb_guide.Interfaces.OnPressedActor
import com.johndev.tmdb_guide.Provider.Services.Resources
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.ItemActorsBinding

class ActorsAdapter(private val actorsList: MutableList<Actor>, val listener: OnPressedActor):
    RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_actors, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actorsList[position]
        val resources = Resources()
        val imagePosterPath = resources.getImageResource(actor.profile_path.toString())
        with(holder) {
            binding.apply {
                setListener(actor)
                tvName.text = actor.name.toString().trim()
                tvDescription.text = actor.known_for_department.toString().trim()
                imgLogo.load(imagePosterPath) {
                    crossfade(true)
                    scale(Scale.FILL)
                    transformations(CircleCropTransformation())
                    placeholder(R.drawable.ic_broken_image)
                }
            }
        }
    }

    override fun getItemCount(): Int = actorsList.size

    fun add(actor: Actor) {
        if (!actorsList.contains(actor)){
            actorsList.add(actor)
            notifyItemInserted(actorsList.size - 1)
        } else{
            update(actor)
        }
    }

    private fun update(actor: Actor){
        val index = actorsList.indexOf(actor)
        if (index != -1){
            actorsList[index] = actor
            notifyItemChanged(index)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemActorsBinding.bind(view)

        fun setListener(actor: Actor){
            binding.root.setOnClickListener {
                listener.OnActorPressed(actor)
            }
        }

    }

}