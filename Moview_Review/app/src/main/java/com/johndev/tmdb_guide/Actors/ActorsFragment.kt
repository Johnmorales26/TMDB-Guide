package com.johndev.tmdb_guide.Actors

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.johndev.tmdb_guide.detailsActorModel.view.DetailsActorActivity
import com.johndev.tmdb_guide.Interfaces.OnPressedActor
import com.johndev.tmdb_guide.Provider.Services.ActorsService
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.adapters.ActorsAdapter
import com.johndev.tmdb_guide.common.entities.ActorEntity
import com.johndev.tmdb_guide.databinding.FragmentActorsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ActorsFragment : Fragment(), OnPressedActor {

    private var _binding: FragmentActorsBinding? = null
    private val binding get() = _binding!!
    private lateinit var actorsAdapter: ActorsAdapter
    private var numPage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureButtons()
        setupRecyclerView()
        lifecycleScope.launch(Dispatchers.Default) {
            inflateRV()
        }
    }

    private fun configureButtons() {
        binding.btnViewMore.setOnClickListener {
            numPage++
            lifecycleScope.launch(Dispatchers.Default) {
                inflateRV()
            }
        }
    }

    private suspend fun inflateRV() = withContext(Dispatchers.Default) {
        lifecycleScope.launch(Dispatchers.Default) {
            val actorsService = ActorsService(activity, requireContext(), actorsAdapter)
            actorsService.getDataActors(numPage)
        }
    }

    private fun setupRecyclerView() {
        val data: MutableList<ActorEntity> = mutableListOf()
        binding.let {
            actorsAdapter = ActorsAdapter(data, this)
            it.rvActors.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = actorsAdapter
            }
        }
    }

    override fun OnActorPressed(actorEntity: ActorEntity) {
        val intent = Intent(context, DetailsActorActivity::class.java).apply {
            putExtra(getString(R.string.key_actor_passed), actorEntity.id.toString().trim())
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}