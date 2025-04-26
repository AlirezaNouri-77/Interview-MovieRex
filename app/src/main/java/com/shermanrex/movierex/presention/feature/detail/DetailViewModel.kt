package com.shermanrex.movierex.presention.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shermanrex.movierex.domain.model.MovieDetailModel
import com.shermanrex.movierex.domain.model.MovieUiState
import com.shermanrex.movierex.domain.model.Result
import com.shermanrex.movierex.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
) : ViewModel() {

    private var _uiState: MutableStateFlow<MovieUiState<MovieDetailModel>> = MutableStateFlow(MovieUiState.Loading)
    val uiState: StateFlow<MovieUiState<MovieDetailModel>> = _uiState.asStateFlow()

    fun handleActions(action: DetailScreenAction) {
        when (action) {
            is DetailScreenAction.GetMovieDetail -> getMovieDetail(action.movieID)
        }
    }

    private fun getMovieDetail(movieID: String) = viewModelScope.launch {
        getMovieDetailUseCase.invoke(movieID).collect {
            when (it) {
                is Result.Failure -> _uiState.value = MovieUiState.Error(it.error)
                is Result.Success -> _uiState.value = MovieUiState.Success(it.data)
            }
        }
    }

}
