package com.example.movieappassessment.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.movieappassessment.BuildConfig
import com.example.movieappassessment.data.local.MovieDatabase
import com.example.movieappassessment.data.remote.api.ApiInterface
import com.example.movieappassessment.data.repository.local.LocalMovieRepositoryImpl
import com.example.movieappassessment.data.repository.main.MainMovieRepositoryImpl
import com.example.movieappassessment.domain.repository.local.LocalMovieRepository
import com.example.movieappassessment.domain.repository.main.MainMovieRepository
import com.example.movieappassessment.utils.Converter
import com.example.movieappassessment.utils.DataBASE
import com.example.movieappassessment.utils.MovieKeyConstant.API_KEY_PARAM
import com.example.movieappassessment.utils.SessionManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun  provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .addInterceptor { chain ->
                val url = chain.request().url.newBuilder().addQueryParameter(API_KEY_PARAM, BuildConfig.API_KEY).build()

                val request = chain.request()
                    .newBuilder()
                    .addHeader("x-localization", "id")
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Cache-Control", "no-store")
                    .addHeader("accept", "application/vnd.github+json")
                    .url(url)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : MovieDatabase =
        Room.databaseBuilder(app, MovieDatabase::class.java, DataBASE.NAME)
            .addTypeConverter(Converter(Gson()))
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @Singleton
    @Provides
    fun bindMainMovieRepository(service: ApiInterface, database: MovieDatabase): MainMovieRepository = MainMovieRepositoryImpl(service = service, database = database)

    @Singleton
    @Provides
    fun bindLocalMovieRepository(sessionManager: SessionManager): LocalMovieRepository = LocalMovieRepositoryImpl(sessionManager = sessionManager)

    @Singleton
    @Provides
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }
}