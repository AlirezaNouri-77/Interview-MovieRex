package com.shermanrex.movierex.domain.repository

import com.shermanrex.movierex.domain.model.MovieDetailModel
import com.shermanrex.movierex.domain.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {

    @GET("movies")
    suspend fun getMovies(): Response<MovieModel>

    @GET("movies/{id}")
    suspend fun getMovieDetail(@Path("id") movieID: String): Response<MovieDetailModel>

    @GET("movies/")
    suspend fun getMovieByName(@Query("q") name: String): Response<MovieModel>

}