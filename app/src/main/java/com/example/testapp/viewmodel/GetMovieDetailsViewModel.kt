package com.example.testapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.pojo.get_movie_details.GetMovieDetailsResponse
import com.example.testapp.model.repository.GetMovieDetailsRepository
import com.example.testapp.model.repository.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetMovieDetailsViewModel @Inject constructor(private val getMovieDetailsRepository: GetMovieDetailsRepository) : ViewModel() {
    private var _response = MutableLiveData<Outcome<GetMovieDetailsResponse?>?>()
    val response: MutableLiveData<Outcome<GetMovieDetailsResponse?>?> = _response

    fun getMovieDetails(
        token: String,
        id: Int
    ) = viewModelScope.launch {
        getMovieDetailsRepository.getMovieDetails(token, id).onStart {
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