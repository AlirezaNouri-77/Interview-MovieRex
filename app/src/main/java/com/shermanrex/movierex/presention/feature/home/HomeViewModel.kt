package com.shermanrex.movierex.presention.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shermanrex.movierex.domain.model.MovieData
import com.shermanrex.movierex.domain.model.MovieUiState
import com.shermanrex.movierex.domain.model.Result
import com.shermanrex.movierex.domain.usecase.GetMoviesUseCase
import com.shermanrex.movierex.domain.util.NetworkConnectivityImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

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
            MovieUiState.Loading,
        )

    fun handleActions(action: HomeScreenAction) {
        when (action) {
            HomeScreenAction.GetMovies -> getMovies()
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            getMoviesUseCase().collect {
                when (it) {
                    is Result.Failure -> _uiState.value = MovieUiState.Error(it.error)
                    is Result.Success -> _uiState.value = MovieUiState.Success(it.data.movie)
                }
            }
        }
    }

}