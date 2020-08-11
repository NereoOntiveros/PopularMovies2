package com.nereoontiveros.popularmovies2.utils

import com.nereoontiveros.popularmovies2.model.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "85ecdecc23fab975c727432b54b0a5e1"

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
}