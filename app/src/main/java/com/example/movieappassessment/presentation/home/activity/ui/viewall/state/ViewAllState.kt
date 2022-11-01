package com.example.movieappassessment.presentation.home.activity.ui.viewall.state

import com.example.movieappassessment.domain.model.Upcoming

data class ViewAllState(
    val upcoming: List<Upcoming> = emptyList(),
    val allowLoadNext: Boolean = false,
    val shouldRefresh: Boolean = false,
    val page: Int = 1,
    val isLoading: Boolean = false
)