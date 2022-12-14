package com.example.movieappassessment.presentation.home.ui.viewall.state

import com.example.movieappassessment.domain.model.Popular
import com.example.movieappassessment.domain.model.Upcoming

data class ViewAllState(
    val upcoming: List<Upcoming> = emptyList(),
    val popular: List<Popular> = emptyList(),
    val allowLoadNext: Boolean = false,
    val page: Int = 1,
    val isLoading: Boolean = false
)