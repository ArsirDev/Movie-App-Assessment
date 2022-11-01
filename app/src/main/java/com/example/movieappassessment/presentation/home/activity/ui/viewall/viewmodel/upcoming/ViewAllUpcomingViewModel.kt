package com.example.movieappassessment.presentation.home.activity.ui.viewall.viewmodel.upcoming

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappassessment.domain.model.Upcoming
import com.example.movieappassessment.domain.repository.main.MainMovieRepository
import com.example.movieappassessment.presentation.home.activity.ui.viewall.event.ViewAllEvent
import com.example.movieappassessment.presentation.home.activity.ui.viewall.state.ViewAllState
import com.example.movieappassessment.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class ViewAllUpcomingViewModel @Inject constructor(
    private val repository: MainMovieRepository
): ViewModel() {
    private val _state = MutableStateFlow(ViewAllState())

    val state get() = _state.asStateFlow()

    fun onEvent(event: ViewAllEvent) {
        when(event) {
            ViewAllEvent.DefaultPage -> {
                getUpcoming(state.value.page)
            }
            ViewAllEvent.LoadMore -> {
                if (state.value.allowLoadNext) {
                    getUpcoming(state.value.page + 1)
                }
            }
        }
    }

    private fun getUpcoming(
        page: Int
    ) {
        repository.getAllViewUpcomingMovie(page, "en-US").onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
                is Result.Success -> {
                    Log.e("TAG", "getUpcoming: ${result.data}", )
                    val allow = page < 1000
                    _state.update {
                        it.copy(
                            isLoading = false,
                            allowLoadNext = allow,
                            page = page,
                            upcoming = if (page == 1) {
                                result.data?.results ?: emptyList()
                            } else {
                                state.value.upcoming + (result.data?.results  ?: emptyList())
                            }
                        )
                    }
                }
                is Result.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope + Dispatchers.IO)
    }

}