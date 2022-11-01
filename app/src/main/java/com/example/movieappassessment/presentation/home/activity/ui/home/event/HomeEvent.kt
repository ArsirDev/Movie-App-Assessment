package com.example.movieappassessment.presentation.home.activity.ui.home.event

sealed class HomeEvent {
    data class SearchUser(val query: String = "") : HomeEvent()
    object LoadMore : HomeEvent()
    object SearchRefresh: HomeEvent()
}
