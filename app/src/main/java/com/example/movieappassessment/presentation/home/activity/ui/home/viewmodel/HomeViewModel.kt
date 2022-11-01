package com.example.movieappassessment.presentation.home.activity.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappassessment.domain.repository.main.MainMovieRepository
import com.example.movieappassessment.presentation.home.activity.ui.home.state.HomeState
import com.example.movieappassessment.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainMovieRepository
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())

    val state get() = _state.asStateFlow()

    fun getUpcoming(
        page: Int,
        language: String
    ) {
        repository.getUpcomingMovie(page, language).onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
                is Result.Success -> {
                    result.data?.let { item ->
                        _state.value = state.value.copy(
                            isLoading = false,
                            upComing = item
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

    fun getPopular(
        page: Int,
        language: String
    ) {
        repository.getPopularMovie(page, language).onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
                is Result.Success -> {
                    result.data?.let { item ->
                        _state.value = state.value.copy(
                            isLoading = false,
                            popular = item
                        )
                        Log.e("TAG", "getPopular: ${result.data}")
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