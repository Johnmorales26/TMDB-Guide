package com.johndev.tmdb_guide.SearchData.SearchCompany

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.johndev.tmdb_guide.detailsCompanyModel.view.DetailsCompanyActivity
import com.johndev.tmdb_guide.Interfaces.OnPressedSearch
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.adapters.SearchAdapter
import com.johndev.tmdb_guide.common.entities.SearchData
import com.johndev.tmdb_guide.SearchData.SearchCommon.SearchValues
import com.johndev.tmdb_guide.common.utils.hideKeyBoard
import com.johndev.tmdb_guide.databinding.FragmentSearchCompanieBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class SearchCompanieFragment : Fragment(), OnPressedSearch {

    private var _binding: FragmentSearchCompanieBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SearchAdapter
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCompanieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        val searchMovie = SearchValues(requireActivity(), requireContext())
        configureButton(searchMovie)
    }

    private fun configureButton(searchMovie: SearchValues) {
        binding.btnSearch.setOnClickListener {
            if (validFields()) {
                hideKeyBoard(requireContext(), binding.root)
                adapter.deleteAll()
                val company = binding.etAutoComplete.text.toString().lowercase(Locale.ROOT).trim()
                binding.btnMore.visibility = VISIBLE
                searchDB(searchMovie, company)
            }
        }
        binding.btnMore.setOnClickListener {
            page++
            val company = binding.etAutoComplete.text.toString().lowercase(Locale.ROOT).trim()
            searchDB(searchMovie, company, page)
        }
    }

    private fun searchDB(searchMovie: SearchValues, company: String, page: Int = 1) {
        lifecycleScope.launch(Dispatchers.Default) {
            searchMovie.getDataCompanies(company, page, adapter)
        }
    }

    private fun setupRecyclerView() {
        val data: MutableList<SearchData> = mutableListOf()
        binding.let {
            adapter = SearchAdapter(data, this)
            it.recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = this@SearchCompanieFragment.adapter
            }
        }
    }

    override fun onSearchPressed(data: SearchData, imgPhoto: View, tvName: View) {
        val intent = Intent(context, DetailsCompanyActivity::class.java).apply {
            putExtra(getString(R.string.key_company_passed), data.id.toString().trim())
        }
        val imgPair: Pair<View, String> = Pair.create(imgPhoto, getString(R.string.tn_imgMovie))
        val namePair: Pair<View, String> = Pair.create(tvName, getString(R.string.tn_tvTitle))
        val options = ActivityOptions.makeSceneTransitionAnimation(activity, imgPair, namePair)
            .toBundle()
        startActivity(intent, options)
    }

    private fun validFields(): Boolean{
        var isValid = true
        //  Evaluando value A
        if (binding.etAutoComplete.text.isNullOrEmpty()){
            binding.tilSearch.run {
                error = getString(R.string.alert_required)
                requestFocus()
            }
            isValid = false
        } else {
            binding.tilSearch.error = null
        }
        return isValid
    }

}