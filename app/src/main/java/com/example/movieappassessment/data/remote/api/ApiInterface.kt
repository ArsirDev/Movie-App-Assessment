package com.example.movieappassessment.data.remote.api

import com.example.movieappassessment.data.remote.dto.PopularResponse
import com.example.movieappassessment.data.remote.dto.UpcomingResponse
import com.example.movieappassessment.utils.MovieEndPoint
import com.example.movieappassessment.utils.MovieKeyConstant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(MovieEndPoint.UPCOMING_MOVIE)
    suspend fun getUpcomingMovies(
        @Query(MovieKeyConstant.KEY_PAGE) page: Int,
        @Query(MovieKeyConstant.KEY_LANGUAGE) language: String
    ): Response<UpcomingResponse>

    @GET(MovieEndPoint.POPULAR_MOVIE)
    suspend fun getPopularMovies(
        @Query(MovieKeyConstant.KEY_PAGE) page: Int,
        @Query(MovieKeyConstant.KEY_LANGUAGE) language: String
    ): Response<PopularResponse>
}