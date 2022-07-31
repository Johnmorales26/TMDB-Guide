package com.johndev.tmdb_guide.DetailsCompany

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import coil.size.Scale
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.Constans
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.ActivityDetailsCompanyBinding

class DetailsCompanyActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDetailsCompanyBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        reciveCompany()
    }

    private fun reciveCompany() {
        val idCompany = intent.getStringExtra(getString(R.string.key_company_passed))
        getDetailsCompany(idCompany.toString().trim())
    }

    private fun getDetailsCompany(url: String): Company {
        var company = Company()
        val queue = Volley.newRequestQueue(this)
        val gson = Gson()
        val url = Constans.URL_BASE + Constans.API_COMPANY_DETAILS + url + Constans.API_KEY_INDEX_SEARCH + Constans.API_KEY
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            company = gson.fromJson(response, Company::class.java)
            updateUI(company)
        }) {
            Toast.makeText(this, "No such movies exists", Toast.LENGTH_LONG).show()
        }
        queue.add(stringRequest)
        return company
    }

    private fun updateUI(companyModel: Company) {
        val imagePosterPath = "${Constans.IMAGES_URL}${companyModel.logo_path}"
        //  Get Image
        binding.imgCompany.load(imagePosterPath) {
            crossfade(true)
            scale(Scale.FILL)
            placeholder(R.drawable.ic_broken_image)
        }
        //  Get Title
        binding.tvTitle.text = companyModel.name.toString().trim()
        //  Get Description
        binding.tvDescription.text = companyModel.description.toString().trim()
        //  Get Origin Country
        binding.tvOriginCountry.text = getString(R.string.label_hometown, companyModel.origin_country.toString().trim())
        //  Get Headquarters
        binding.tvHeadquartes.text = getString(R.string.label_headquarters, companyModel.headquarters.toString().trim())
        //  Get Parent Company
        binding.tvParentCompany.text = getString(R.string.label_parent_company, companyModel.parent_company.toString().trim())
    }
}