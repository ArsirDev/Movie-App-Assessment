package com.example.movieappassessment.presentation.home.activity.ui.home.state

import com.example.movieappassessment.data.remote.dto.Genres
import com.example.movieappassessment.domain.model.Popular
import com.example.movieappassessment.domain.model.Upcoming

data class HomeState(
    val upComing: List<Upcoming> = emptyList(),
    val popular: List<Popular> = emptyList(),
    val genre: List<Genres> = emptyList(),
    val isLoading: Boolean = false
)