package com.shermanrex.movierex.presention.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shermanrex.movierex.data.model.MovieData
import com.shermanrex.movierex.data.model.MovieUiState
import com.shermanrex.movierex.data.model.Result
import com.shermanrex.movierex.domain.GetMovieByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getMovieByNameUseCase: GetMovieByNameUseCase,
) : ViewModel() {

    private var _uiState: MutableStateFlow<MovieUiState<List<MovieData>>> = MutableStateFlow(MovieUiState.Initial)
    val uiState = _uiState.asStateFlow()


    fun handleAction(action: SearchScreenAction) {
        when (action) {
            is SearchScreenAction.GetMovie -> getMovie(action.name)
        }
    }

    fun getMovie(name: String) = viewModelScope.launch {
        getMovieByNameUseCase.invoke(name).collect {
            _uiState.value = MovieUiState.Loading
            when (it) {
                is Result.Failure -> _uiState.value = MovieUiState.Error(it.error)
                is Result.Success -> _uiState.value = MovieUiState.Success(it.data.movie)
            }
        }
    }

}
