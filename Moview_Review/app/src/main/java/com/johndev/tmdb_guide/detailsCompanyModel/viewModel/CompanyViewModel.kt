package com.johndev.tmdb_guide.detailsCompanyModel.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.tmdb_guide.common.entities.CompanyEntity
import com.johndev.tmdb_guide.detailsCompanyModel.model.CompanyRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class CompanyViewModel : ViewModel() {

    private val repository = CompanyRepository()

    private val result = MutableLiveData<CompanyEntity>()
    fun getResult() = result

    fun consultCompanyByID(id: Int) {
        viewModelScope.launch {
            result.value = repository.consultCompanyByID(id)
        }
    }

    fun saveCompany(companyEntity: CompanyEntity) {
        viewModelScope.launch {
            try {
                repository.saveCompany(companyEntity)
                consultCompanyByID(companyEntity.id!!.toInt())
            } catch (e: Exception) {
            }
        }
    }

}