package com.example.movieappassessment.domain.repository.local

import kotlinx.coroutines.flow.Flow

interface LocalMovieRepository {

    suspend fun setFirstInstallStatus(status: Boolean)

    suspend fun getFirstInstallStatus(): Flow<Boolean>
}