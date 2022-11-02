package com.example.movieappassessment.presentation.home.ui.explore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappassessment.domain.repository.main.MainMovieRepository
import com.example.movieappassessment.presentation.home.ui.explore.event.ExploreEvent
import com.example.movieappassessment.presentation.home.ui.explore.state.ExploreState
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
class ExploreViewModel @Inject constructor(
    private val repository: MainMovieRepository
): ViewModel() {

    private var _state = MutableStateFlow(ExploreState())
    val state get() = _state.asStateFlow()

    init {
        getGenre()
    }

    fun onEvent(event: ExploreEvent) {
        when(event) {
            is ExploreEvent.SearchUser -> {
                getSearchMovie(event.query ,state.value.page)
            }
            ExploreEvent.SearchLoadMore ->  {
                if (state.value.allowLoadNext) {
                    getSearchMovie(state.value.searchQuery, state.value.page + 1)
                }
            }
            else -> {}
        }
    }

    private fun getSearchMovie(
        query: String = "",
        page: Int,
    ) {
        repository.getSearchMovie(query, page,"en-US").onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    val allow = page < result.data?.totalPages ?: 0
                    _state.update {
                        it.copy(
                            isLoading = false,
                            allowLoadNext = allow,
                            page = page,
                            searchQuery = query,
                            search = if (page == 1) {
                                result.data?.results ?: emptyList()
                            } else {
                                state.value.search + (result.data?.results ?: emptyList())
                            }
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope + IO)
    }

    private fun getGenre(){
        repository.getGenre().onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    result.data?.genres?.let { item ->
                        _state.value = state.value.copy(
                            isLoading = false,
                            genre = item
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope + IO)
    }

    fun onGenreEVent(event: ExploreEvent){
        when(event) {
            is ExploreEvent.DiscoverGenre -> {
                getDiscoverMovie(
                    event.genre,
                    state.value.discoverPage
                )
            }
            ExploreEvent.DiscoverLoadMore -> {
               if (state.value.allowLoadNext) {
                   getDiscoverMovie(
                       state.value.genreDiscover,
                       state.value.discoverPage + 1
                   )
               }
            }
            else -> {}
        }
    }

    private fun getDiscoverMovie(
        with_genres: String,
        page: Int,
    ) {
        repository.getDiscoverMovie(
            with_genres = with_genres,
            page = page,
            false,
            "en-US"
        ).onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    val allow = page < result.data?.totalPages ?: 0
                    _state.update {
                        it.copy(
                            isLoading = false,
                            allowLoadNext = allow,
                            discoverPage = page,
                            genreDiscover = with_genres,
                            discover = if (page == 1) {
                                result.data?.results ?: emptyList()
                            } else {
                                state.value.discover + (result.data?.results ?: emptyList())
                            }
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope + IO)
    }
}