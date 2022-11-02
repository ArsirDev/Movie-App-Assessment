package com.example.movieappassessment.data.remote.api

import com.example.movieappassessment.data.remote.dto.DetailResponse
import com.example.movieappassessment.data.remote.dto.DiscoverMovieResponse
import com.example.movieappassessment.data.remote.dto.GenreResponse
import com.example.movieappassessment.data.remote.dto.PopularResponse
import com.example.movieappassessment.data.remote.dto.SearchMovieResponse
import com.example.movieappassessment.data.remote.dto.UpcomingResponse
import com.example.movieappassessment.data.remote.dto.VideoResponse
import com.example.movieappassessment.utils.MovieEndPoint
import com.example.movieappassessment.utils.MovieKeyConstant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET(MovieEndPoint.GENRE_MOVIE)
    suspend fun getGenreMovies(): Response<GenreResponse>

    @GET(MovieEndPoint.DETAIL_MOVIE)
    suspend fun getDetailMovies(
        @Path("movie_id") movie_id: Int
    ): Response<DetailResponse>

    @GET(MovieEndPoint.VIDEO_MOVIE)
    suspend fun getVideoMovies(
        @Path("movie_id") movie_id: Int
    ): Response<VideoResponse>

    @GET(MovieEndPoint.SEARCH_MOVIE)
    suspend fun getSearchMovies(
        @Query(MovieKeyConstant.KEY_QUERY) query: String,
        @Query(MovieKeyConstant.KEY_PAGE) page: Int,
        @Query(MovieKeyConstant.INCLUDE_ADULT) include_adult: Boolean,
        @Query(MovieKeyConstant.KEY_LANGUAGE) language: String
    ): Response<SearchMovieResponse>

    @GET(MovieEndPoint.DISCOVER_MOVIE)
    suspend fun getDiscoverMovies(
        @Query(MovieKeyConstant.KEY_WITH_GENRE) with_genres: String,
        @Query(MovieKeyConstant.KEY_PAGE) page: Int,
        @Query(MovieKeyConstant.INCLUDE_ADULT) include_adult: Boolean,
        @Query(MovieKeyConstant.KEY_LANGUAGE) language: String
    ): Response<DiscoverMovieResponse>

}