package com.johndev.tmdb_guide.detailsActorModel.viewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.entities.ActorEntity
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.detailsActorModel.model.ActorRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ActorViewModel : ViewModel() {

    private val repository = ActorRepository()

    private val result = MutableLiveData<ActorEntity>()
    fun getResult() = result

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    fun consultActorByCode(id: Int) {
        viewModelScope.launch {
            result.value = repository.consultActorByID(id)
        }
    }

    fun saveActor(actorEntity: ActorEntity) {
        viewModelScope.launch {
            try {
                repository.saveActor(actorEntity)
                consultActorByCode(actorEntity.id!!.toInt())
            } catch (e: Exception) {
            }
        }
    }

}