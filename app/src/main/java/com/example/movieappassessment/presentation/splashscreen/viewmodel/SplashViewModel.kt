package com.example.movieappassessment.presentation.splashscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappassessment.domain.repository.local.LocalMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: LocalMovieRepository
): ViewModel() {

    private var _state = MutableSharedFlow<Boolean>()

    val state get() = _state.asSharedFlow()

    private val _isLoading = MutableStateFlow(false)

    val isLoading get() = _isLoading.asStateFlow()

    private fun onLoading() = flow<Boolean> {
        delay(5000)
        _isLoading.value = false
    }.launchIn(viewModelScope + IO)

    private fun getFirstInstall() = viewModelScope.launch {
        repository.getFirstInstallStatus().collectLatest { status ->
            _state.emit(status)
        }
    }

    init {
        onLoading()
        getFirstInstall()
    }
}