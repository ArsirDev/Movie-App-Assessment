package com.example.movieappassessment.data.repository.main

import android.util.Log
import androidx.room.withTransaction
import com.example.movieappassessment.data.local.MovieDatabase
import com.example.movieappassessment.data.remote.api.ApiInterface
import com.example.movieappassessment.data.remote.dto.UpcomingResponse
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
}