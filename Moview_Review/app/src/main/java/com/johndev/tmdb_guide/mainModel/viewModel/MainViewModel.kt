package com.johndev.tmdb_guide.mainModel.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.tmdb_guide.common.entities.DataRequestEntity
import com.johndev.tmdb_guide.mainModel.model.MainRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    private val resultPopular = MutableLiveData<DataRequestEntity>()
    fun getResultPopular() = resultPopular
    private val resultCurrent = MutableLiveData<DataRequestEntity>()
    fun getResultCurrent() = resultCurrent
    private val resultDrama = MutableLiveData<DataRequestEntity>()
    fun getResultDrama() = resultDrama
    private val resultBest = MutableLiveData<DataRequestEntity>()
    fun getResultBest() = resultBest

    fun consultDataPopularByID(id: Int) {
        viewModelScope.launch {
            resultPopular.value = repository.consultDataRequestByID(id)
        }
    }

    fun consultDataCurrentByID(id: Int) {
        viewModelScope.launch {
            resultCurrent.value = repository.consultDataRequestByID(id)
        }
    }

    fun consultDataDramaByID(id: Int) {
        viewModelScope.launch {
            resultCurrent.value = repository.consultDataRequestByID(id)
        }
    }

    fun consultDataBestByID(id: Int) {
        viewModelScope.launch {
            resultBest.value = repository.consultDataRequestByID(id)
        }
    }

    fun saveDataPopular(dataRequestEntity: DataRequestEntity) {
        viewModelScope.launch {
            try {
                repository.saveDataRequest(dataRequestEntity)
                consultDataPopularByID(dataRequestEntity.id.toString().trim().toInt())
            } catch (e: Exception){}
        }
    }

    fun saveDataCurent(dataRequestEntity: DataRequestEntity) {
        viewModelScope.launch {
            try {
                repository.saveDataRequest(dataRequestEntity)
                consultDataCurrentByID(dataRequestEntity.id.toString().trim().toInt())
            } catch (e: Exception){ }
        }
    }

    fun saveDataDrama(dataRequestEntity: DataRequestEntity) {
        viewModelScope.launch {
            try {
                repository.saveDataRequest(dataRequestEntity)
                consultDataDramaByID(dataRequestEntity.id.toString().trim().toInt())
            } catch (e: Exception){}
        }
    }

    fun saveDataBest(dataRequestEntity: DataRequestEntity) {
        viewModelScope.launch {
            try {
                repository.saveDataRequest(dataRequestEntity)
                consultDataBestByID(dataRequestEntity.id.toString().trim().toInt())
            } catch (e: Exception){}
        }
    }

}