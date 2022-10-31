package com.example.movieappassessment.di

import android.content.Context
import com.example.movieappassessment.data.remote.api.ApiInterface
import com.example.movieappassessment.data.repository.local.LocalMovieRepositoryImpl
import com.example.movieappassessment.data.repository.main.MainMovieRepositoryImpl
import com.example.movieappassessment.domain.repository.local.LocalMovieRepository
import com.example.movieappassessment.domain.repository.main.MainMovieRepository
import com.example.movieappassessment.utils.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun bindMainMovieRepository(service: ApiInterface): MainMovieRepository = MainMovieRepositoryImpl(service = service)

    @Singleton
    @Provides
    fun bindLocalMovieRepository(sessionManager: SessionManager): LocalMovieRepository = LocalMovieRepositoryImpl(sessionManager = sessionManager)

    @Singleton
    @Provides
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }
}