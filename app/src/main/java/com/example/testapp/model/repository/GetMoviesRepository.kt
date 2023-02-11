package com.example.testapp.model.repository

import com.example.testapp.model.pojo.get_movies.GetMoviesResponse
import com.example.testapp.retrofit.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMoviesRepository @Inject constructor(private val apiInterface: ApiInterface)  {
    fun getMovies(
        token: String
    ): Flow<GetMoviesResponse?> = flow{
        emit(apiInterface.getMovies(token))
    }.flowOn(Dispatchers.IO)
}