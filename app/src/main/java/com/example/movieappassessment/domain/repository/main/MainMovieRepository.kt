package com.example.movieappassessment.domain.repository.main

import com.example.movieappassessment.data.remote.dto.UpcomingResponse
import com.example.movieappassessment.domain.model.Popular
import com.example.movieappassessment.domain.model.Upcoming
import com.example.movieappassessment.utils.Result
import kotlinx.coroutines.flow.Flow

interface MainMovieRepository {

    fun getUpcomingMovie(
        page: Int,
        language: String
    ): Flow<Result<List<Upcoming>>>

    fun getAllViewUpcomingMovie(
        page: Int,
        language: String
    ): Flow<Result<UpcomingResponse>>

    fun getPopularMovie(
        page: Int,
        language: String
    ): Flow<Result<List<Popular>>>
}