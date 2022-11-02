package com.example.movieappassessment.presentation.detail.state

import com.example.movieappassessment.data.remote.dto.DetailResponse
import com.example.movieappassessment.data.remote.dto.VideoResultsItem

data class DetailState(
    val detail: DetailResponse? = null,
    val video: List<VideoResultsItem>? = emptyList(),
    val isLoading: Boolean = false
)