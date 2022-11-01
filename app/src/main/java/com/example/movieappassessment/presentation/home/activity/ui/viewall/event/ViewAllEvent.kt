package com.example.movieappassessment.presentation.home.activity.ui.viewall.event

sealed class ViewAllEvent {
    object LoadMore : ViewAllEvent()
    object DefaultPage: ViewAllEvent()
}
