package com.example.movieappassessment.presentation.home.ui.viewall.event

sealed class ViewAllEvent {
    object LoadMore : ViewAllEvent()
    object DefaultPage: ViewAllEvent()
}
