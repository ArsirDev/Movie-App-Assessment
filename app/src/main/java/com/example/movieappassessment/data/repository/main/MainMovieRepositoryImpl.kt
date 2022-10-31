package com.example.movieappassessment.data.repository.main

import com.example.movieappassessment.data.remote.api.ApiInterface
import com.example.movieappassessment.domain.repository.main.MainMovieRepository

class MainMovieRepositoryImpl constructor(
    private val service: ApiInterface
): MainMovieRepository {

}