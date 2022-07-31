package com.johndev.tmdb_guide.celebritiesModule.view

import android.app.Person
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.johndev.tmdb_guide.OnPersonListener
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.adapters.PersonAdapter
import com.johndev.tmdb_guide.common.adapters.ResultSearchAdapter
import com.johndev.tmdb_guide.common.entities.ResultPerson
import com.johndev.tmdb_guide.common.entities.ResultSearch
import com.johndev.tmdb_guide.databinding.FragmentCelebritiesBinding
import com.johndev.tmdb_guide.databinding.FragmentSearchBinding
import com.johndev.tmdb_guide.detailsActorModule.view.DetailsActorActivity
import com.johndev.tmdb_guide.mainModule.view.MainActivity
import com.johndev.tmdb_guide.mainModule.view.MainActivity.Companion.actorViewModel

class CelebritiesFragment : Fragment(), OnPersonListener {

    private var _binding: FragmentCelebritiesBinding? = null
    private val binding get() = _binding!!
    private lateinit var personAdapter: PersonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCelebritiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
        configureSearching()
        setupObservers()
    }

    private fun setupObservers() {
        actorViewModel.actorResult().observe(viewLifecycleOwner) { actor ->
            if (actor != null) {
                setupRecyclerview(actor)
            }
        }
    }

    private fun configureSearching() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                actorViewModel.getMoviesByQuery(s.toString().trim())
            }
        })
    }

    private fun setupRecyclerview(listMovies: MutableList<ResultPerson> = mutableListOf()) {
        personAdapter = PersonAdapter(listMovies, this)
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = personAdapter
        }
    }

    override fun OnClick(person: ResultPerson, imgView: View, name: View) {
        val intent = Intent(context, DetailsActorActivity::class.java).apply {
            putExtra(getString(R.string.key_actor_id), person.id.toString().trim())
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}