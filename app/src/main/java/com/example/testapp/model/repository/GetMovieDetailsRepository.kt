package com.example.testapp.model.repository

import com.example.testapp.model.pojo.get_movie_details.GetMovieDetailsResponse
import com.example.testapp.retrofit.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieDetailsRepository @Inject constructor(private val apiInterface: ApiInterface)  {
    fun getMovieDetails(
        token: String,
        id: Int
    ): Flow<GetMovieDetailsResponse?> = flow{
        emit(apiInterface.getMovieDetails(token,id))
    }.flowOn(Dispatchers.IO)
}