package com.example.movieappassessment.domain.repository.main

import com.example.movieappassessment.data.remote.dto.DetailResponse
import com.example.movieappassessment.data.remote.dto.Genres
import com.example.movieappassessment.data.remote.dto.PopularResponse
import com.example.movieappassessment.data.remote.dto.UpcomingResponse
import com.example.movieappassessment.data.remote.dto.VideoResponse
import com.example.movieappassessment.domain.model.Popular
import com.example.movieappassessment.domain.model.Upcoming
import com.example.movieappassessment.utils.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Path
import retrofit2.http.Query

interface MainMovieRepository {

    fun getUpcomingMovie(
        page: Int,
        language: String
    ): Flow<Result<List<Upcoming>>>

    fun getAllViewUpcomingMovie(
        page: Int,
        language: String
    ): Flow<Result<UpcomingResponse>>

    fun getAllViewPopularMovie(
        page: Int,
        language: String
    ): Flow<Result<PopularResponse>>

    fun getGenre(): Flow<Result<List<Genres>>>

    fun getPopularMovie(
        page: Int,
        language: String
    ): Flow<Result<List<Popular>>>

    fun getDetailMovie(
        movie_id: Int,
    ): Flow<Result<DetailResponse>>

    fun getVideoMovie(
        movie_id: Int
    ): Flow<Result<VideoResponse>>
}