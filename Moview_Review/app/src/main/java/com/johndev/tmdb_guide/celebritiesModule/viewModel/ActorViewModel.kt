package com.johndev.tmdb_guide.celebritiesModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.tmdb_guide.common.entities.ResultPerson
import com.johndev.tmdb_guide.mainModule.view.MainActivity.Companion.searchContext
import com.johndev.tmdb_guide.celebritiesModule.model.ActorRepository
import kotlinx.coroutines.launch

class ActorViewModel : ViewModel() {

    private val repository = ActorRepository(searchContext)

    fun actorResult() = actorResult

    fun getMoviesByQuery(query: String) {
        viewModelScope.launch {
            actorResult.value = repository.getMoviesByQuery(query)
        }
    }

    companion object {
        val actorResult = MutableLiveData<MutableList<ResultPerson>>()
    }

}