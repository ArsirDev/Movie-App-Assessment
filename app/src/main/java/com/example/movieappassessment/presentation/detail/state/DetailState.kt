package com.example.movieappassessment.presentation.detail.state

import com.example.movieappassessment.data.remote.dto.DetailResponse

data class DetailState(
    val detail: DetailResponse? = null,
    val isLoading: Boolean = false
)