package com.example.testapp.retrofit

import com.example.testapp.model.pojo.get_movie_details.GetMovieDetailsResponse
import com.example.testapp.model.pojo.get_movies.GetMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiInterface {
    @GET("discover/movie")
    suspend fun getMovies(
        @Header("Authorization") token: String,
    ): GetMoviesResponse?

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ): GetMovieDetailsResponse?
}