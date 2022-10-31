package com.example.movieappassessment.data.repository.local

import com.example.movieappassessment.domain.repository.local.LocalMovieRepository
import com.example.movieappassessment.utils.SessionManager
import kotlinx.coroutines.flow.Flow

class LocalMovieRepositoryImpl constructor(
    private val sessionManager: SessionManager
): LocalMovieRepository {
    override suspend fun setFirstInstallStatus(status: Boolean) {
        return sessionManager.setFirstInstall(status)
    }

    override suspend fun getFirstInstallStatus(): Flow<Boolean> {
        return sessionManager.getFirstInstall
    }
}