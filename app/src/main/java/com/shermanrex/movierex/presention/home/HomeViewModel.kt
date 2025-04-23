package com.shermanrex.movierex.presention.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shermanrex.movierex.data.model.MovieData
import com.shermanrex.movierex.data.model.MovieUiState
import com.shermanrex.movierex.data.model.Result
import com.shermanrex.movierex.domain.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private var _uiState: MutableStateFlow<MovieUiState<List<MovieData>>> = MutableStateFlow(MovieUiState.Loading)
    var uiState: StateFlow<MovieUiState<List<MovieData>>> = _uiState
        .onStart {
            getMovies()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            MovieUiState.Loading
        )

    fun handleAction(action: HomeScreenAction) {
        when (action) {
            HomeScreenAction.GetMovies -> getMovies()
        }
    }

    private fun getMovies() = viewModelScope.launch {
        getMoviesUseCase.invoke().collect {
            when (it) {
                is Result.Failure -> _uiState.value = MovieUiState.Error(it.error)
                is Result.Success -> _uiState.value = MovieUiState.Success(it.data.movie)
            }
        }
    }

}
