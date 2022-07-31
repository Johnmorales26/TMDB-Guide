package com.johndev.tmdb_guide.TV.MoreTV

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.johndev.tmdb_guide.Provider.Services.TVService
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.TV.CardTVAdapter
import com.johndev.tmdb_guide.Interfaces.OnTVPressed
import com.johndev.tmdb_guide.TV.DetailsTV.DetailsTVActivity
import com.johndev.tmdb_guide.TV.TV
import com.johndev.tmdb_guide.databinding.ActivityMoreTvactivityBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoreTVActivity : AppCompatActivity(), OnTVPressed {

    private lateinit var binding: ActivityMoreTvactivityBinding
    private lateinit var adapter: CardTVAdapter
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreTvactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra(getString(R.string.key_tv_url_passed)).toString()
        println("URL Passed --> $url")
        setupRecyclerView()
        configureButtons(url)
        lifecycleScope.launch(Dispatchers.Default) {
            inflateList(url)
        }
    }

    private fun configureButtons(url: String) {
        binding.btnMore.setOnClickListener {
            page++
            lifecycleScope.launch(Dispatchers.Default) {
                inflateList(url)
            }
        }
    }

    private suspend fun inflateList(url: String) = withContext(Dispatchers.Default) {
        val tvServiceReciente = TVService(this@MoreTVActivity, this@MoreTVActivity, adapter)
        lifecycleScope.launch(Dispatchers.Default) {
            tvServiceReciente.getDataTV(url, page)
        }
    }

    private fun setupRecyclerView() {
        val data: MutableList<TV> = mutableListOf()
        binding.let {
            adapter = CardTVAdapter(data, this)
            it.recyclerView.apply {
                layoutManager = GridLayoutManager(this@MoreTVActivity, 3, LinearLayoutManager.VERTICAL, false)
                adapter = this@MoreTVActivity.adapter
            }
        }
    }

    override fun OnPressedTV(tv: TV) {
        val intent = Intent(this, DetailsTVActivity::class.java).apply {
            putExtra(getString(R.string.key_tv_passed), tv.id.toString().trim())
        }
        startActivity(intent)
    }
}