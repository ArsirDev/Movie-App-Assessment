package com.example.movieappassessment.presentation.home.ui.explore.state

import com.example.movieappassessment.data.remote.dto.DiscoverResultsItem
import com.example.movieappassessment.data.remote.dto.Genres
import com.example.movieappassessment.data.remote.dto.SearchResultsItem

data class ExploreState(
    val searchQuery: String = "",
    val genreDiscover: String = "",
    val genre: List<Genres> = emptyList(),
    val search: List<SearchResultsItem> = emptyList(),
    val discover: List<DiscoverResultsItem> = emptyList(),
    val allowLoadNext: Boolean = false,
    val page: Int = 1,
    val discoverPage: Int = 1,
    val isLoading: Boolean = false
)
