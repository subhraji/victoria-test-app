package com.example.testapp.retrofit

import com.example.testapp.model.pojo.get_movies.GetMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {
    @GET("discover/movie")
    suspend fun getMovies(
        @Header("Authorization") token: String,
    ): GetMoviesResponse?
}