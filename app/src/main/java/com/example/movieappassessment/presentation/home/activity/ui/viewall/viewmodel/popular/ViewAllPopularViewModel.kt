package com.example.movieappassessment.presentation.home.activity.ui.viewall.viewmodel.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappassessment.domain.repository.main.MainMovieRepository
import com.example.movieappassessment.presentation.home.activity.ui.viewall.event.ViewAllEvent
import com.example.movieappassessment.presentation.home.activity.ui.viewall.state.ViewAllState
import com.example.movieappassessment.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class ViewAllPopularViewModel @Inject constructor(
    private val repository: MainMovieRepository
): ViewModel() {

    private val _state = MutableStateFlow(ViewAllState())

    val state get() = _state.asStateFlow()

    fun onEvent(event: ViewAllEvent) {
        when(event) {
            ViewAllEvent.DefaultPage ->  {
                getPopular(state.value.page)
            }
            ViewAllEvent.LoadMore ->  {
                if (state.value.allowLoadNext) {
                    getPopular(state.value.page + 1)
                }
            }
        }
    }

    private fun getPopular(
        page: Int
    ) {
        repository.getAllViewPopularMovie(page, "en-US").onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
                is Result.Success -> {
                    val allow = page < 1000
                    _state.update { get ->
                        get.copy(
                            isLoading = false,
                            allowLoadNext = allow,
                            page = page,
                            popular = if (page == 1) {
                                result.data?.results ?: emptyList()
                            } else {
                                state.value.popular + (result.data?.results  ?: emptyList())
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
        }.launchIn(viewModelScope + IO)
    }
}