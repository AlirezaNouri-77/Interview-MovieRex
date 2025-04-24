package com.shermanrex.movierex.domain.model

sealed interface MovieUiState<out T> {
    data object Initial : MovieUiState<Nothing>
    data object Loading : MovieUiState<Nothing>
    data class Success<T>(var data: T) : MovieUiState<T>
    data class Error(var error: NetworkError) : MovieUiState<Nothing>
}
