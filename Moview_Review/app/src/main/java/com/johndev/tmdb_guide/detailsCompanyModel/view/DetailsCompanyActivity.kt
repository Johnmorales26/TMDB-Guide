package com.johndev.tmdb_guide.detailsCompanyModel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.size.Scale
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.entities.CompanyEntity
import com.johndev.tmdb_guide.common.utils.getImageResource
import com.johndev.tmdb_guide.databinding.ActivityDetailsCompanyBinding
import com.johndev.tmdb_guide.detailsActorModel.viewModel.ActorViewModel
import com.johndev.tmdb_guide.detailsCompanyModel.viewModel.CompanyViewModel

class DetailsCompanyActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDetailsCompanyBinding
    private lateinit var companyViewModel: CompanyViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupObservers()
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
        reciveCompany()
    }

    private fun setupViewModel() {
        companyViewModel = ViewModelProvider(this)[CompanyViewModel::class.java]
    }

    private fun setupObservers() {
        companyViewModel.getResult().observe(this) { company ->
            if (company == null) {
                binding.apply {
                    tvTitle.text = "Compañia No Disponible"
                }
            } else {
                binding.topAppBar.title = company.name.toString().trim()
                //  Get Image
                binding.imgCompany.load(getImageResource(company.logo_path.toString())) {
                    crossfade(true)
                    scale(Scale.FILL)
                    placeholder(R.drawable.ic_broken_image)
                }
                //  Get Title
                binding.tvTitle.text = company.name.toString().trim()
                //  Get Description
                binding.tvDescription.text = company.description.toString().trim()
                //  Get Origin Country
                binding.tvOriginCountry.text = getString(R.string.label_hometown, company.origin_country.toString().trim())
                //  Get Headquarters
                binding.tvHeadquartes.text = getString(R.string.label_headquarters, company.headquarters.toString().trim())
                //  Get Parent Company
                binding.tvParentCompany.text = getString(R.string.label_parent_company, company.parent_company.toString().trim())
            }
        }
    }

    private fun reciveCompany() {
        val idCompany = intent.getStringExtra(getString(R.string.key_company_passed))
        getDetailsCompany(idCompany.toString().trim())
        companyViewModel.consultCompanyByID(idCompany.toString().toInt())
    }

    private fun getDetailsCompany(url: String): CompanyEntity {
        var companyRequest = CompanyEntity()
        val queue = Volley.newRequestQueue(this)
        val gson = Gson()
        val url = Constans.URL_BASE + Constans.API_COMPANY_DETAILS + url + Constans.API_KEY_INDEX_SEARCH + Constans.API_KEY
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            companyRequest = gson.fromJson(response, CompanyEntity::class.java)
            companyViewModel.saveCompany(companyRequest)
        }) {
            Toast.makeText(this, "No such movies exists", Toast.LENGTH_LONG).show()
        }
        queue.add(stringRequest)
        return companyRequest
    }
}