package com.shermanrex.movierex.presention.feature.detail

sealed interface DetailScreenAction {
    data class GetMovieDetail(var movieID: String) : DetailScreenAction
}