package com.example.movieappassessment.presentation.sliderscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappassessment.domain.repository.local.LocalMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(
    private val repository: LocalMovieRepository
): ViewModel() {
    private val _isLoading = MutableStateFlow(false)

    val isLoading get() = _isLoading.asStateFlow()

    private fun onLoading() = flow<Boolean> {
        delay(5000)
        _isLoading.value = false
    }

    fun setFirstInstall(
        status: Boolean
    ) = viewModelScope.launch {
        repository.setFirstInstallStatus(status)
    }

    init {
        onLoading()
    }
}