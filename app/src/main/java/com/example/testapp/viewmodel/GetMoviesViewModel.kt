package com.example.testapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.pojo.get_movies.GetMoviesResponse
import com.example.testapp.model.repository.GetMoviesRepository
import com.example.testapp.model.repository.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetMoviesViewModel @Inject constructor(private val getMoviesRepository: GetMoviesRepository) : ViewModel() {
    private var _response = MutableLiveData<Outcome<GetMoviesResponse?>?>()
    val response: MutableLiveData<Outcome<GetMoviesResponse?>?> = _response

    fun getMovies(
        token: String
    ) = viewModelScope.launch {
        getMoviesRepository.getMovies(token).onStart {
            _response.value = Outcome.loading(true)
        }.catch {
            _response.value = Outcome.Failure(it)
        }.collect {
            _response.value = Outcome.success(it)
        }
    }

    fun navigationComplete(){
        _response.value = null
    }
}