package com.example.movieappassessment.domain.repository.main

import com.example.movieappassessment.data.remote.dto.DetailResponse
import com.example.movieappassessment.data.remote.dto.DiscoverMovieResponse
import com.example.movieappassessment.data.remote.dto.GenreResponse
import com.example.movieappassessment.data.remote.dto.Genres
import com.example.movieappassessment.data.remote.dto.PopularResponse
import com.example.movieappassessment.data.remote.dto.SearchMovieResponse
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

    fun getGenre(): Flow<Result<GenreResponse>>

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

    fun getSearchMovie(
        query: String,
        page: Int,
        language: String
    ): Flow<Result<SearchMovieResponse>>

    fun getDiscoverMovie(
        with_genres: String,
        page: Int,
        include_adult: Boolean,
        language: String
    ): Flow<Result<DiscoverMovieResponse>>
}