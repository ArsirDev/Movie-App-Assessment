package com.example.movieappassessment.data.repository.main

import androidx.room.withTransaction
import com.example.movieappassessment.data.local.MovieDatabase
import com.example.movieappassessment.data.remote.api.ApiInterface
import com.example.movieappassessment.data.remote.dto.DetailResponse
import com.example.movieappassessment.data.remote.dto.DiscoverMovieResponse
import com.example.movieappassessment.data.remote.dto.GenreResponse
import com.example.movieappassessment.data.remote.dto.PopularResponse
import com.example.movieappassessment.data.remote.dto.SearchMovieResponse
import com.example.movieappassessment.data.remote.dto.UpcomingResponse
import com.example.movieappassessment.data.remote.dto.VideoResponse
import com.example.movieappassessment.domain.model.Popular
import com.example.movieappassessment.domain.model.Upcoming
import com.example.movieappassessment.domain.repository.main.MainMovieRepository
import com.example.movieappassessment.utils.Result
import com.example.movieappassessment.utils.networkBindingResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainMovieRepositoryImpl constructor(
    private val service: ApiInterface,
    private val database: MovieDatabase
) : MainMovieRepository {

    override fun getUpcomingMovie(page: Int, language: String): Flow<Result<List<Upcoming>>> =
        networkBindingResource(
            query = {
                database.upcomingDao().getAllUpcoming()
            },
            fetch = {
                delay(2000)
                service.getUpcomingMovies(page, language)
            },
            saveFetchResult = { upcoming ->
                database.withTransaction {
                    database.upcomingDao().deleteAllUpcoming()
                    upcoming.body()?.toUpcomingList()?.let { item ->
                        database.upcomingDao().insertUpcoming(item)
                    }
                }
            }
        )

    override fun getAllViewUpcomingMovie(
        page: Int,
        language: String
    ): Flow<Result<UpcomingResponse>> = flow {
        emit(Result.Loading())

        val result = service.getUpcomingMovies(page, language)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getAllViewPopularMovie(
        page: Int,
        language: String
    ): Flow<Result<PopularResponse>> = flow {
        emit(Result.Loading())

        val result = service.getPopularMovies(page, language)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getGenre(): Flow<Result<GenreResponse>> = flow {
        emit(Result.Loading())

        val result = service.getGenreMovies()

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getPopularMovie(page: Int, language: String): Flow<Result<List<Popular>>> =
        networkBindingResource(
            query = {
                database.popularDao().getAllPopular()
            },
            fetch = {
                delay(2000)
                service.getPopularMovies(page, language)
            },
            saveFetchResult = { popular ->
                database.withTransaction {
                    database.popularDao().deleteAllPopular()
                    popular.body()?.toPopularList()?.let { item ->
                        database.popularDao().insertPopular(item)
                    }
                }
            }
        )

    override fun getDetailMovie(movie_id: Int): Flow<Result<DetailResponse>> = flow {
        emit(Result.Loading())

        val result = service.getDetailMovies(movie_id)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getVideoMovie(movie_id: Int): Flow<Result<VideoResponse>> = flow {
        emit(Result.Loading())

        val result = service.getVideoMovies(movie_id)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getSearchMovie(
        query: String,
        page: Int,
        language: String
    ): Flow<Result<SearchMovieResponse>> = flow {
        emit(Result.Loading())

        val result = service.getSearchMovies(query, page, false, language)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getDiscoverMovie(
        with_genres: String,
        page: Int,
        include_adult: Boolean,
        language: String
    ): Flow<Result<DiscoverMovieResponse>> = flow {
        emit(Result.Loading())

        val result = service.getDiscoverMovies(with_genres, page, false, language)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }
}