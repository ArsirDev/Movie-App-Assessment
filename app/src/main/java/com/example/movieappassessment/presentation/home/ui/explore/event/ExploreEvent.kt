package com.example.movieappassessment.presentation.home.ui.explore.event

sealed class ExploreEvent {
    data class SearchUser(val query: String = "") : ExploreEvent()
    object SearchLoadMore : ExploreEvent()
    data class DiscoverGenre(val genre: String = "") : ExploreEvent()
    object DiscoverLoadMore : ExploreEvent()
}
