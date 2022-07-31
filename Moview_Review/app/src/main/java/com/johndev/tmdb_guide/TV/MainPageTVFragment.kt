package com.johndev.tmdb_guide.TV

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.johndev.tmdb_guide.common.utils.Constans.API_TV_AIRING_TODAY
import com.johndev.tmdb_guide.common.utils.Constans.API_TV_LATEST
import com.johndev.tmdb_guide.common.utils.Constans.API_TV_ON_THE_AIR
import com.johndev.tmdb_guide.common.utils.Constans.API_TV_POPULAR
import com.johndev.tmdb_guide.common.utils.Constans.API_TV_TOP_RATED
import com.johndev.tmdb_guide.Interfaces.OnTVPressed
import com.johndev.tmdb_guide.Provider.Services.TVService
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.TV.DetailsTV.DetailsTVActivity
import com.johndev.tmdb_guide.TV.MoreTV.MoreTVActivity
import com.johndev.tmdb_guide.databinding.FragmentMainPageTVBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPageTVFragment : Fragment(), OnTVPressed {

    private var _binding: FragmentMainPageTVBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterTVAlAire: CardTVAdapter
    private lateinit var adapterTVEnAire: CardTVAdapter
    private lateinit var adapterTVPopular: CardTVAdapter
    private lateinit var adapterTVReciente: CardTVAdapter
    private lateinit var adapterTVTopRated: CardTVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageTVBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        configureButtons()
        lifecycleScope.launch(Dispatchers.Default) {
            inflateList()
        }
    }

    private fun configureButtons() {
        binding.btnTVAlDia.setOnClickListener { moreTVSeries("tv/airing_today") }
        binding.btnTVEnAire.setOnClickListener { moreTVSeries("tv/on_the_air") }
        binding.btnTVPopular.setOnClickListener { moreTVSeries("tv/popular") }
        binding.btnTVTopRated.setOnClickListener { moreTVSeries("tv/top_rated") }
    }

    private fun setupRecyclerViews() {
        setupRVAlAire()
        setupTVEnAire()
        setupTVPopular()
        setupTVReciente()
        setupTVTopRated()
    }

    private suspend fun inflateList() = withContext(Dispatchers.Default) {
        //  -->
        val tvServiceReciente = TVService(activity, requireContext(), adapterTVReciente)
        lifecycleScope.launch(Dispatchers.Default) {
            tvServiceReciente.getDataTV(API_TV_LATEST)
        }
        //  -->
        val tvServiceAlAire = TVService(activity, requireContext(), adapterTVAlAire)
        lifecycleScope.launch(Dispatchers.Default) {
            tvServiceAlAire.getDataTV(API_TV_AIRING_TODAY)
        }
        //  -->
        val tvServiceEnAire = TVService(activity, requireContext(), adapterTVEnAire)
        lifecycleScope.launch(Dispatchers.Default) {
            tvServiceEnAire.getDataTV(API_TV_ON_THE_AIR)
        }
        //  -->
        val tvServicePopular = TVService(activity, requireContext(), adapterTVPopular)
        lifecycleScope.launch(Dispatchers.Default) {
            tvServicePopular.getDataTV(API_TV_POPULAR)
        }
        //  -->
        val tvServiceTopRated = TVService(activity, requireContext(), adapterTVTopRated)
        lifecycleScope.launch(Dispatchers.Default) {
            tvServiceTopRated.getDataTV(API_TV_TOP_RATED)
        }
    }

    private fun setupTVTopRated() {
        val data: MutableList<TV> = mutableListOf()
        binding.let {
            adapterTVTopRated = CardTVAdapter(data, this)
            it.rvTVTopRated.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@MainPageTVFragment.adapterTVTopRated
            }
        }
    }

    private fun setupTVReciente() {
        val data: MutableList<TV> = mutableListOf()
        binding.let {
            adapterTVReciente = CardTVAdapter(data, this)
            it.rvTVReciente.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@MainPageTVFragment.adapterTVReciente
            }
        }
    }

    private fun setupTVPopular() {
        val data: MutableList<TV> = mutableListOf()
        binding.let {
            adapterTVPopular = CardTVAdapter(data, this)
            it.rvTVPopular.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@MainPageTVFragment.adapterTVPopular
            }
        }
    }

    private fun setupTVEnAire() {
        val data: MutableList<TV> = mutableListOf()
        binding.let {
            adapterTVEnAire = CardTVAdapter(data, this)
            it.rvTVEnAire.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@MainPageTVFragment.adapterTVEnAire
            }
        }
    }

    private fun setupRVAlAire() {
        val data: MutableList<TV> = mutableListOf()
        binding.let {
            adapterTVAlAire = CardTVAdapter(data, this)
            it.rvTVAlAire.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@MainPageTVFragment.adapterTVAlAire
            }
        }
    }

    private fun moreTVSeries(url: String) {
        val intent = Intent(context, MoreTVActivity::class.java).apply {
            putExtra(getString(R.string.key_tv_url_passed), url.trim())
        }
        startActivity(intent)
    }

    override fun OnPressedTV(tv: TV) {
        val intent = Intent(context, DetailsTVActivity::class.java).apply {
            putExtra(getString(R.string.key_tv_passed), tv.id.toString().trim())
        }
        startActivity(intent)
    }
}